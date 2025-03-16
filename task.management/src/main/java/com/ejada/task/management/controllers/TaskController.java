package com.ejada.task.management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ejada.task.management.dto.task.TaskCreateUpdateRequest;
import com.ejada.task.management.dto.task.TaskResponse;
import com.ejada.task.management.enums.Priority;
import com.ejada.task.management.enums.Status;
import com.ejada.task.management.services.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RestControllerAdvice
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public List<TaskResponse> getAllTasks(@RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority) {
        return taskService.getTasks(status, priority);
    }
    
    @GetMapping({"/{taskId}"})
    public List<TaskResponse> getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @PostMapping()
    public ResponseEntity<String> createTask(@RequestBody TaskCreateUpdateRequest taskRequest) {
        taskService.createTask(taskRequest);
        return ResponseEntity.ok("Task created successfully.");
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskCreateUpdateRequest taskRequest) {

        taskService.updateTask(taskId, taskRequest);
        return ResponseEntity.ok("Task updated successfully.");
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam Status newStatus) {

        taskService.updateStatus(taskId, newStatus);
        return ResponseEntity.ok("Task status updated successfully.");
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
