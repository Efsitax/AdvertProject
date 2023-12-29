package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.User;
import com.kadirugurlu.advertproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User>{

    private final UserRepository userRepository;

    @Override
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.empty();
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
