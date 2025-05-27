package com.x10.workshop7.controller;

import com.x10.workshop7.model.Task;
import com.x10.workshop7.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Task>>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskService.getAllTasks(pageable);
        ApiResponse<Page<Task>> response = ApiResponse.<Page<Task>>builder()
                .code("2000")
                .message("Success")
                .data(tasks)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(ApiResponse.<Task>builder()
                        .code("2000")
                        .message("Success")
                        .data(task)
                        .build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@Valid @RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.ok(ApiResponse.<Task>builder()
                .code("2000")
                .message("Success")
                .data(created)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task)
                .map(updated -> ResponseEntity.ok(ApiResponse.<Task>builder()
                        .code("2000")
                        .message("Success")
                        .data(updated)
                        .build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok(ApiResponse.<Void>builder()
                    .code("2000")
                    .message("Success")
                    .data(null)
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
