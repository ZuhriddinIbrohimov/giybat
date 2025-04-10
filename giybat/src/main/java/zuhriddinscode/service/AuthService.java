package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.enums.ProfileRoles;
import zuhriddinscode.exps.AppBadException;
import zuhriddinscode.repository.ProfileRepository;
import zuhriddinscode.repository.ProfileRoleRepository;
import zuhriddinscode.types.GeneralStatus;
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
        profileRoleService.create(entity.getId(), ProfileRoles.ROLE_USER);
        emailSendingService.sendRegistration(registrationDTO.getUsername(), entity.getId());
        return "Successfully registered";
        //SEND
    }

    public String regVerification(Integer profileId) {
        ProfileEntity profile = profileService.getById(profileId);
        if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
            profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
            return "Verification finished";
        }
    throw new AppBadException("Verification failed");
    }
}