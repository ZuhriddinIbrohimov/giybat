package zuhriddinscode.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration( @Valid @RequestBody RegistrationDTO registrationDTO) {
        return ResponseEntity.ok().body(authService.registration(registrationDTO));
    }

    @PostMapping ("/registration/verification/{id}")
    public ResponseEntity<String> regVerification ( @Valid @PathVariable{"id"} Integer profileId){
        return ResponseEntity.ok().body ( authService.regVerification(profileId) );
    }
}