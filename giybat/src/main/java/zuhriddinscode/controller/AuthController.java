package zuhriddinscode.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zuhriddinscode.dto.AuthDTO;
import zuhriddinscode.dto.ProfileDTO;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration ( @Valid @RequestBody RegistrationDTO registrationDTO) {
        return ResponseEntity.ok().body ( authService.registration(registrationDTO));
    }

    @GetMapping ("/registration/verification/{token}")
    public ResponseEntity<String> regVerification ( @PathVariable("token") String token ) {
        return ResponseEntity.ok().body ( authService.regVerification(token) );
    }

    @PostMapping ("/login")
    public ResponseEntity<ProfileDTO> login ( @Valid @RequestBody AuthDTO dto ){
        return ResponseEntity.ok().body(authService.login(dto));
    }
}