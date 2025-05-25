package com.x10.workshop6.controller;

import com.x10.workshop6.model.UserRequest;
import com.x10.workshop6.model.UserResponse;
import com.x10.workshop6.model.ApiResponse;
import com.x10.workshop6.model.User;
import com.x10.workshop6.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

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
}
