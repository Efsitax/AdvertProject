package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.DTO.AccountDTO;
import com.kadirugurlu.advertproject.DTO.UserDTO;
import com.kadirugurlu.advertproject.Entity.Account;
import com.kadirugurlu.advertproject.Entity.User;
import com.kadirugurlu.advertproject.Service.AccountService;
import com.kadirugurlu.advertproject.Service.AuthenticationService;
import com.kadirugurlu.advertproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user-account")
public class UserAccountController {

    private final UserService userService;
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @GetMapping("/user")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        if(userService.read(id).isPresent()) {
            return ResponseEntity.ok(userService.read(id).orElse(null));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO entity, @PathVariable Long id){
        if(userService.read(id).isPresent()) {
            User user = authenticationService.fillUser(entity,null).user;
            return ResponseEntity.ok(userService.update(user, id));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        if(userService.read(id).isPresent()) {
            userService.delete(id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/account")
    public List<Account> getAllAccounts(){
        return accountService.getAll();
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        if(accountService.read(id).isPresent()) {
            return ResponseEntity.ok(accountService.read(id).orElse(null));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/account/update/{id}")
    public ResponseEntity<Account> updateAccount(@RequestBody AccountDTO entity, @PathVariable Long id){
        if(accountService.read(id).isPresent()) {
            Account account = new Account();
            account.setUsername(entity.getUsername());
            account.setPassword(entity.getPassword());
            return ResponseEntity.ok(accountService.update(account, id));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/account/delete/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id){
        if(accountService.read(id).isPresent()) {
            accountService.delete(id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }
}
