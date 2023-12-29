package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.Account;
import com.kadirugurlu.advertproject.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements BaseService<Account> {

    private final AccountRepository accountRepository;

    @Override
    public Account create(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Optional<Account> read(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account update(Account entity, Long id) {
        Account account = read(id).orElse(null);

        if(account != null) {
            account.setUsername(entity.getUsername());
            account.setPassword(entity.getPassword());
            return accountRepository.save(account);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(accountRepository::delete);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
