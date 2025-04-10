package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.exps.AppBadException;
import zuhriddinscode.repository.ProfileRepository;
import zuhriddinscode.types.GeneralStatus;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registration(RegistrationDTO registrationDTO) {
        // 1. validation
        // 2. user mb da bormi ?

        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(registrationDTO.getUsername());
        if (optional.isEmpty()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRepository.delete(profile);
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
        return "Successfully registered";
    }
}