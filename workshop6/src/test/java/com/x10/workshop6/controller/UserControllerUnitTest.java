package com.x10.workshop6.controller;

import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.model.User;
import com.x10.workshop6.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    private UserRequest req;
    private User user;

    @BeforeEach
    void setUp() {
        req = new UserRequest();
        req.setUsername("john");
        req.setEmail("john@example.com");
        req.setAge(25);
        req.setPassword("password");
        req.setMobile("0123456789");
        user = new User(1L, "john", "john@example.com", 25, "password", "0123456789");
    }

    @Test
    void testCreateUser() {
        Mockito.when(userService.createUser(any(UserRequest.class))).thenReturn(user);
        ResponseEntity<?> response = userController.createUser(req);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("john"));
    }

    @Test
    void testGetUser() {
        Mockito.when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<?> response = userController.getUser(1L);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("john"));
    }

    @Test
    void testGetAllUsers() {
        User user2 = new User(2L, "jane", "jane@example.com", 30, "password2", "0987654321");
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user, user2));
        ResponseEntity<?> response = userController.getAllUsers();
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("john"));
        assertTrue(response.getBody().toString().contains("jane"));
    }

    @Test
    void testUpdateUser() {
        User updated = new User(1L, "johnny", "johnny@example.com", 26, "newpass", "0123456789");
        Mockito.when(userService.updateUser(eq(1L), any(UserRequest.class))).thenReturn(updated);
        req.setUsername("johnny");
        req.setEmail("johnny@example.com");
        req.setAge(26);
        req.setPassword("newpass");
        req.setMobile("0123456789");
        ResponseEntity<?> response = userController.updateUser(1L, req);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("johnny"));
    }

    @Test
    void testDeleteUser() {
        Mockito.doNothing().when(userService).deleteUser(1L);
        ResponseEntity<?> response = userController.deleteUser(1L);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("User deleted successfully"));
    }
}
