package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.Entity.User;
import com.kadirugurlu.advertproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);
    }
}
