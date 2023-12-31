package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.DTO.LoginDTO;
import com.kadirugurlu.advertproject.DTO.RegisterDTO;
import com.kadirugurlu.advertproject.DTO.UserDTO;
import com.kadirugurlu.advertproject.Entity.Account;
import com.kadirugurlu.advertproject.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AccountService accountService;

    public Boolean register(RegisterDTO entity){
        Account eaccount = accountService.findByUsername(entity.getUsername()).orElse(null);
        if(eaccount != null){
            return false;
        }
        else {
            UserAccount userAccount = fillUser(null, entity);
            User user = userAccount.user;
            Account account = userAccount.account;

            return userService.create(user) != null && accountService.create(account) != null;
        }
    }

    public Boolean login(LoginDTO entity){
        Account account = accountService.findByUsername(entity.getUsername()).orElse(null);
        return account != null && account.getPassword().equals(entity.getPassword());
    }

    public UserAccount fillUser(UserDTO entity, RegisterDTO entity2) {

        UserAccount userAccount = new UserAccount();
        User user = new User();

        if(entity2 == null){
            user.setName(entity.getName());
            user.setSurname(entity.getSurname());
            user.setEmail(entity.getEmail());
            user.setPhone(entity.getPhone());
            user.setAddress(entity.getAddress());
            userAccount.user = user;
        }
        else{

            user.setName(entity2.getName());
            user.setSurname(entity2.getSurname());
            user.setEmail(entity2.getEmail());
            user.setPhone(entity2.getPhone());
            user.setAddress(entity2.getAddress());
            userAccount.user = user;

            Account account = new Account();
            account.setUsername(entity2.getUsername());
            account.setPassword(entity2.getPassword());
            account.setUser(user);
            userAccount.account = account;

        }
        return userAccount;
    }
}
