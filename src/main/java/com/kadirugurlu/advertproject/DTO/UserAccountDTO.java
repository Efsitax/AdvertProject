package com.kadirugurlu.advertproject.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UserAccountDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private Date birthdate;
}
