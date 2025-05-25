package com.x10.workshop5.controller;

import com.x10.workshop5.model.UserRequest;
import com.x10.workshop5.model.UserResponse;
import com.x10.workshop5.model.ApiResponse;
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
    @PostMapping("/user")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = new UserResponse(
            userRequest.getUsername(),
            userRequest.getEmail(),
            userRequest.getAge()
        );
        ApiResponse<UserResponse> response = new ApiResponse<>(
            "2000",
            "Success",
            userResponse
        );
        return ResponseEntity.ok(response);
    }
}
