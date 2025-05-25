package com.x10.workshop4.controller;

import com.x10.workshop4.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalculatorService calculatorService;


    @Test
    void testAdd() throws Exception {
        when(calculatorService.add(2.0, 3.0)).thenReturn(5.0);
        mockMvc.perform(post("/api/v1/calculator/add?a=2&b=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").value(5.0));
    }

    @Test
    void testSubtract() throws Exception {
        when(calculatorService.subtract(5.0, 2.0)).thenReturn(3.0);
        mockMvc.perform(post("/api/v1/calculator/subtract?a=5&b=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").value(3.0));
    }

    @Test
    void testMultiply() throws Exception {
        when(calculatorService.multiply(4.0, 2.5)).thenReturn(10.0);
        mockMvc.perform(post("/api/v1/calculator/multiply?a=4&b=2.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").value(10.0));
    }

    @Test
    void testDivide() throws Exception {
        when(calculatorService.divide(10.0, 2.0)).thenReturn(5.0);
        mockMvc.perform(post("/api/v1/calculator/divide?a=10&b=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").value(5.0));
    }
}
