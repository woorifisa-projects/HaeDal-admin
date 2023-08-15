package dev.syntax.backend.admin.service;

import dev.syntax.backend.admin.dto.response.UserResponse;
import dev.syntax.backend.admin.model.User;
import dev.syntax.backend.admin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }
}
