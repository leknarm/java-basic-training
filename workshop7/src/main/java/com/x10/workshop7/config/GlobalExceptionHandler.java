package com.x10.workshop7.config;

import com.x10.workshop7.controller.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .code("4000")
                .message("ValidationException")
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .code("4000")
                .message("ConstraintViolationException")
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<String>> handleAllExceptions(Exception ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code("5000")
                .message(ex.getClass().getSimpleName())
                .data(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
