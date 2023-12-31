package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.DTO.LoginDTO;
import com.kadirugurlu.advertproject.DTO.RegisterDTO;
import com.kadirugurlu.advertproject.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDTO registerDTO){
        if(authenticationService.register(registerDTO)) {
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO entity){
        if(authenticationService.login(entity)) {
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }
}
