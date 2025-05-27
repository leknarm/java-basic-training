package com.x10.workshop6.service;

import com.x10.workshop6.model.User;
import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(UserService.class)
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setUsername("john");
        userRequest.setEmail("john@example.com");
        userRequest.setAge(25);
        userRequest.setPassword("password");
        userRequest.setMobile("0123456789");
    }

    @Test
    void testCreateUser() {
        User saved = userService.createUser(userRequest);
        assertNotNull(saved.getId());
        assertEquals("john", saved.getUsername());
        assertEquals("john@example.com", saved.getEmail());
    }

    @Test
    void testGetUserById() {
        User saved = userService.createUser(userRequest);
        User found = userService.getUserById(saved.getId());
        assertEquals(saved.getId(), found.getId());
        assertEquals("john", found.getUsername());
    }

    @Test
    void testGetAllUsers() {
        userService.createUser(userRequest);
        UserRequest userRequest2 = new UserRequest();
        userRequest2.setUsername("jane");
        userRequest2.setEmail("jane@example.com");
        userRequest2.setAge(30);
        userRequest2.setPassword("password2");
        userRequest2.setMobile("0987654321");
        userService.createUser(userRequest2);
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void testUpdateUser() {
        User saved = userService.createUser(userRequest);
        UserRequest updateReq = new UserRequest();
        updateReq.setUsername("johnny");
        updateReq.setEmail("johnny@example.com");
        updateReq.setAge(26);
        updateReq.setPassword("newpass");
        updateReq.setMobile("0123456789");
        User updated = userService.updateUser(saved.getId(), updateReq);
        assertEquals("johnny", updated.getUsername());
        assertEquals("johnny@example.com", updated.getEmail());
        assertEquals(26, updated.getAge());
    }

    @Test
    void testDeleteUser() {
        User saved = userService.createUser(userRequest);
        userService.deleteUser(saved.getId());
        assertEquals(0, userRepository.count());
    }
}
