package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.User;
import com.kadirugurlu.advertproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return userRepository.findById(id);
    }

    @Override
    public User update(User entity, Long id) {
        User user = read(id).orElse(null);

        if(user != null) {
            user.setName(entity.getName());
            user.setSurname(entity.getSurname());
            user.setPhone(entity.getPhone());
            user.setAddress(entity.getAddress());
            user.setBirthdate(entity.getBirthdate());
            user.setEmail(entity.getEmail());
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(userRepository::delete);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
