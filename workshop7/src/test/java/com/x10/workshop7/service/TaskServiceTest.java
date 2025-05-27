package com.x10.workshop7.service;

import com.x10.workshop7.model.Task;
import com.x10.workshop7.model.TaskStatus;
import com.x10.workshop7.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        when(taskRepository.findAll()).thenReturn(List.of(task));
        List<Task> result = taskService.getAllTasks();
        assertThat(result).hasSize(1).contains(task);
    }

    @Test
    void testGetAllTasksWithPageable() {
        Task task = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Pageable pageable = PageRequest.of(0, 10);
        when(taskRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(task)));
        Page<Task> result = taskService.getAllTasks(pageable);
        assertThat(result.getContent()).hasSize(1).contains(task);
    }

    @Test
    void testGetTaskById() {
        Task task = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> result = taskService.getTaskById(1L);
        assertThat(result).isPresent().contains(task);
    }

    @Test
    void testCreateTask() {
        Task task = new Task(null, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Task saved = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        when(taskRepository.save(task)).thenReturn(saved);
        Task result = taskService.createTask(task);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testUpdateTask() {
        Task existing = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        Task update = new Task(null, "Updated", "Desc", LocalDate.now(), TaskStatus.DONE);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class))).thenReturn(update);
        Optional<Task> result = taskService.updateTask(1L, update);
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Updated");
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(1L, "Title", "Desc", LocalDate.now(), TaskStatus.OPEN);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);
        boolean result = taskService.deleteTask(1L);
        assertThat(result).isTrue();
    }
}
