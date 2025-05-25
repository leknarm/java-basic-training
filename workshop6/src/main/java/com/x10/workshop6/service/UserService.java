package com.x10.workshop6.service;

import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.model.User;
import com.x10.workshop6.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
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
}
