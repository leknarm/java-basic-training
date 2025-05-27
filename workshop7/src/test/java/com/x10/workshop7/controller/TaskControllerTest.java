package com.x10.workshop7.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.x10.workshop7.model.Task;
import com.x10.workshop7.model.TaskStatus;
import com.x10.workshop7.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTasks() throws Exception {
        Task task = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Mockito.when(taskService.getAllTasks(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(task)));
        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"))
                .andExpect(jsonPath("$.data.content[0].title").value("Title"));
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));
        mockMvc.perform(get("/api/v1/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Title"));
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task(null, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Task created = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Mockito.when(taskService.createTask(any(Task.class))).thenReturn(created);
        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task updated = new Task(1L, "Updated", "Desc", LocalDate.now(), TaskStatus.DONE);
        Mockito.when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(Optional.of(updated));
        mockMvc.perform(put("/api/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Updated"));
    }

    @Test
    void testDeleteTask() throws Exception {
        Mockito.when(taskService.deleteTask(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("2000"));
    }
}
