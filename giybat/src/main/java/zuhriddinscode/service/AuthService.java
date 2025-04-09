package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.exps.AppBadException;
import zuhriddinscode.repository.ProfileRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    public String registration ( RegistrationDTO registrationDTO ){
    // 1. validation
    // 2. user mb da bormi ?

       Optional<ProfileEntity>  optional = profileRepository.findByUsernameAndVisibleTrue(registrationDTO.getUsername());
       if (optional.isEmpty()) {
           throw new AppBadException("Username already exists");
       }


        return "successfully registered";
    }
}