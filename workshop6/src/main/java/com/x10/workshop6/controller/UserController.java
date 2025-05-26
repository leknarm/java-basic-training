package com.x10.workshop6.controller;

import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.model.UserResponse;
import com.x10.workshop6.model.ApiResponse;
import com.x10.workshop6.model.User;
import com.x10.workshop6.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserRequest userRequest) {
        User savedUser = userService.createUser(userRequest);
        UserResponse userResponse = new UserResponse(
            savedUser.getId(),
            savedUser.getUsername(),
            savedUser.getEmail(),
            savedUser.getAge()
        );
        ApiResponse<UserResponse> response = new ApiResponse<>(
            "2000",
            "Success",
            userResponse
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAge());
        ApiResponse<UserResponse> response = new ApiResponse<>("2000", "Success", userResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers().stream()
            .map(u -> new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getAge()))
            .toList();
        ApiResponse<List<UserResponse>> response = new ApiResponse<>("2000", "Success", users);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        User updatedUser = userService.updateUser(id, userRequest);
        UserResponse userResponse = new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getAge());
        ApiResponse<UserResponse> response = new ApiResponse<>("2000", "Success", userResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ApiResponse<Void> response = new ApiResponse<>("2000", "User deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
