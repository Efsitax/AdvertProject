package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.DTO.UserAccountDTO;
import com.kadirugurlu.advertproject.Service.AuthenticationService;
import com.kadirugurlu.advertproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserAccountDTO userAccountDTO){
        if(authenticationService.createUserAccount(userAccountDTO)) {
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

}
