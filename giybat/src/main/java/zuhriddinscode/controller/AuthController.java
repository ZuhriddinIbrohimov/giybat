package zuhriddinscode.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zuhriddinscode.dto.AppResponse;
import zuhriddinscode.dto.AuthDTO;
import zuhriddinscode.dto.ProfileDTO;
import zuhriddinscode.dto.RegistrationDTO;
import zuhriddinscode.enums.AppLanguage;
import zuhriddinscode.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<AppResponse<String>> registration(@Valid @RequestBody RegistrationDTO registrationDTO,
                                                            @RequestHeader(value = "Accept-Language",defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(authService.registration(registrationDTO, lang));
    }

    @GetMapping("/registration/verification/{token}")
    public ResponseEntity<String> regVerification(@PathVariable("token") String token,
                                                  @RequestParam  AppLanguage lang) {
        return ResponseEntity.ok().body(authService.regVerification(token,lang));
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody AuthDTO dto,
                                            @RequestHeader (value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {
        return ResponseEntity.ok().body(authService.login(dto, lang));
    }
}