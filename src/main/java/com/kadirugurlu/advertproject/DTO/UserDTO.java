package com.kadirugurlu.advertproject.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private String email;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private Date birthdate;
}
