package com.x10.workshop6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.model.User;
import com.x10.workshop6.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        UserRequest req = new UserRequest();
        req.setUsername("john");
        req.setEmail("john@example.com");
        req.setAge(25);
        req.setPassword("password");
        req.setMobile("0123456789");
        User user = new User(1L, "john", "john@example.com", 25, "password", "0123456789");
        Mockito.when(userService.createUser(any(UserRequest.class))).thenReturn(user);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.username").value("john"));
    }

    @Test
    void testGetUser() throws Exception {
        User user = new User(1L, "john", "john@example.com", 25, "password", "0123456789");
        Mockito.when(userService.getUserById(1L)).thenReturn(user);
        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.username").value("john"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "john", "john@example.com", 25, "password", "0123456789");
        User user2 = new User(2L, "jane", "jane@example.com", 30, "password2", "0987654321");
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user1, user2));
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].id").value(2L));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserRequest req = new UserRequest();
        req.setUsername("johnny");
        req.setEmail("johnny@example.com");
        req.setAge(26);
        req.setPassword("newpass");
        req.setMobile("0123456789");
        User updated = new User(1L, "johnny", "johnny@example.com", 26, "newpass", "0123456789");
        Mockito.when(userService.updateUser(eq(1L), any(UserRequest.class))).thenReturn(updated);
        mockMvc.perform(put("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.username").value("johnny"));
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(1L);
        mockMvc.perform(delete("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"))
                .andExpect(jsonPath("$.message").value("User deleted successfully"));
    }
}
