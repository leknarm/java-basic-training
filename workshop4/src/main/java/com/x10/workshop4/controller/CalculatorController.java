package com.x10.workshop4.controller;

import com.x10.workshop4.model.ApiResponse;
import com.x10.workshop4.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Double>> add(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.add(a, b);
        return ResponseEntity.ok(new ApiResponse<>("2000", "Success", result));
    }

    @PostMapping("/subtract")
    public ResponseEntity<ApiResponse<Double>> subtract(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.subtract(a, b);
        return ResponseEntity.ok(new ApiResponse<>("2000", "Success", result));
    }

    @PostMapping("/multiply")
    public ResponseEntity<ApiResponse<Double>> multiply(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.multiply(a, b);
        return ResponseEntity.ok(new ApiResponse<>("2000", "Success", result));
    }

    @PostMapping("/divide")
    public ResponseEntity<ApiResponse<Double>> divide(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.divide(a, b);
        return ResponseEntity.ok(new ApiResponse<>("2000", "Success", result));
    }
}
