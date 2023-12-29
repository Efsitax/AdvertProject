package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.DTO.UserAccountDTO;
import com.kadirugurlu.advertproject.Entity.Account;
import com.kadirugurlu.advertproject.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AccountService accountService;

    public Boolean createUserAccount(UserAccountDTO entity){
        User user = new User();
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setPhone(entity.getPhone());
        user.setAddress(entity.getAddress());
        user.setBirthdate(entity.getBirthdate());
        user.setEmail(entity.getEmail());

        Account account = new Account();
        account.setUsername(entity.getUsername());
        account.setPassword(entity.getPassword());
        account.setUser(user);

        if(userService.create(user) != null && accountService.create(account) != null){
            return true;
        }
        else {
            return false;
        }
    }
}
