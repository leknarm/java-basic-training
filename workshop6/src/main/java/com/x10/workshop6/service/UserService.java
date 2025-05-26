package com.x10.workshop6.service;

import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.model.User;
import com.x10.workshop6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest) {
        User user = new User(
            null,
            userRequest.getUsername(),
            userRequest.getEmail(),
            userRequest.getAge(),
            userRequest.getPassword(),
            userRequest.getMobile()
        );
        return saveUser(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserRequest userRequest) {
        User user = getUserById(id);
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        user.setPassword(userRequest.getPassword());
        user.setMobile(userRequest.getMobile());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
