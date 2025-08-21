package zuhriddinscode.service;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zuhriddinscode.dto.AppResponse;
import zuhriddinscode.dto.AuthDTO;
import zuhriddinscode.dto.ProfileDTO;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.dto.sms.SmsVerificationDTO;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.enums.AppLanguage;
import zuhriddinscode.enums.ProfileRole;
import zuhriddinscode.exps.AppBadException;
import zuhriddinscode.repository.ProfileRepository;
import zuhriddinscode.repository.ProfileRoleRepository;
import zuhriddinscode.types.GeneralStatus;
import zuhriddinscode.util.JwtUtil;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    @Autowired
    private ProfileRoleService profileRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public AppResponse<String> registration(RegistrationDTO registrationDTO, AppLanguage lang) {
        // 1. validation
        // 2. user mb da bormi ?

        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(registrationDTO.getUsername());
        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRoleService.deleteRoles(profile.getId());
                profileRepository.delete(profile);
                //send sms/email
            } else {
                throw new AppBadException(resourceBundleService.getMessage("email.phone.exists", lang));
            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(registrationDTO.getName());
        entity.setUsername(registrationDTO.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        entity.setStatus(GeneralStatus.IN_REGISTRATION);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);

        // INSERT ROLES
        profileRoleService.create(entity.getId(), ProfileRole.ROLE_USER);
        emailSendingService.sendRegistrationEmail(registrationDTO.getUsername(), entity.getId(), lang);
        return new AppResponse <> ( resourceBundleService.getMessage("email.confirm.send",lang));
        //SEND
    }

    public String registrationEmailVerification(String token, AppLanguage lang) {
        try {
            Integer profileId = JwtUtil.decodeRegVerToken(token);
            ProfileEntity profile = profileService.getById(profileId);
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
                return resourceBundleService.getMessage("verification.finish",lang);
            }
        } catch (JwtException e) {
        }
        throw new AppBadException(resourceBundleService.getMessage("verification.fail", lang));
    }

    public ProfileDTO login(@Valid AuthDTO dto, AppLanguage lang) {
       Optional<ProfileEntity> optional =  profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isEmpty()){
            throw new AppBadException(resourceBundleService.getMessage("username.password.wrong",lang));
        }
        ProfileEntity profile = optional.get();
        if (!bCryptPasswordEncoder.matches(dto.getPassword(), profile.getPassword())){
            throw new AppBadException(resourceBundleService.getMessage("username.password.wrong",lang));
        }
        if ( !profile.getStatus().equals(GeneralStatus.ACTIVE) ){
            throw new AppBadException(resourceBundleService.getMessage("wrong.status",lang));
        }
        ProfileDTO response = new ProfileDTO();
        response.setName(profile.getName());
        response.setUsername(profile.getUsername());
        response.setRoleList(profileRoleRepository.getAllRolesListByProfileId(profile.getId()));   //          -----------------------------------------------
        response.setJwt(JwtUtil.encode( profile.getId()));
        return response;
    }

    public String registrationSmsVerification(SmsVerificationDTO dto, AppLanguage lang) {
        Optional <ProfileEntity>  optional = profileRepository.findByUsernameAndVisibleTrue(dto.getCode());
        if (optional.isEmpty()){
            throw new AppBadException(resourceBundleService.getMessage("profile.not.found", lang));
        }
        ProfileEntity profile = optional.get();
        if (!profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
            throw new AppBadException(resourceBundleService.getMessage("verification.fail",lang));
        }
        /// code check
        ///  ACTIVE




        return null;
    }
}