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
        return Optional.empty();
    }

    @Override
    public Account update(Account entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
