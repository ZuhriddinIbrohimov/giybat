package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.repository.ProfileRepository;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    public String registration (RegistrationDTO registrationDTO ){
    // 1. validation
    // 2. user mb da bormi ?



        return "";
    }

}