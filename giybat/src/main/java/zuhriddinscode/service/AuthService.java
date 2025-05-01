package zuhriddinscode.service;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zuhriddinscode.dto.AuthDTO;
import zuhriddinscode.dto.ProfileDTO;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.entity.ProfileEntity;
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

    public String registration(RegistrationDTO registrationDTO) {
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
                throw new AppBadException("Username already exists");
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
        emailSendingService.sendRegistrationEmail(registrationDTO.getUsername(), entity.getId());
        return "Successfully registered";
        //SEND
    }

    public String regVerification(String token) {
        try {
            Integer profileId = JwtUtil.decodeRegVerToken(token);
            ProfileEntity profile = profileService.getById(profileId);
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
                return "Verification finished";
            }
        } catch (JwtException e) {

        }
        throw new AppBadException("Verification failed");
    }

    public ProfileDTO login(@Valid AuthDTO dto) {
       Optional<ProfileEntity> optional =  profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isEmpty()){
            throw new AppBadException("Username or password is wrong");
        }
        ProfileEntity profile = optional.get();
        if (!bCryptPasswordEncoder.matches(dto.getPassword(), profile.getPassword())){
            throw new AppBadException("Username or password is wrong");
        }
        if ( !profile.getStatus().equals(GeneralStatus.ACTIVE) ){
            throw new AppBadException("Status is wrong");
        }
        ProfileDTO response = new ProfileDTO();
        response.setName(profile.getName());
        response.setUsername(profile.getUsername());
        response.setRoleList(profileRoleRepository.getAllRolesListByProfileId(profile.getId()));   //          -----------------------------------------------
        response.setJwt(JwtUtil.encode(profile.getId(), response.getRoleList()));
        return response;
    }
}