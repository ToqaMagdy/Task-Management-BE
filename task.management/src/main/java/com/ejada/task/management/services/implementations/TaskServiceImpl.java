package com.ejada.task.management.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejada.task.management.dto.task.TaskCreateUpdateRequest;
import com.ejada.task.management.dto.task.TaskResponse;
import com.ejada.task.management.entities.Task;
import com.ejada.task.management.enums.Priority;
import com.ejada.task.management.enums.Status;
import com.ejada.task.management.repositories.TaskRepository;
import com.ejada.task.management.services.TaskService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;

    private final UserService userService;

    @Override
    public void createTask(TaskCreateUpdateRequest taskRequest) {
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .dueDate(taskRequest.getDueDate())
                .priority(taskRequest.getPriority())
                .createdBy(userService.getAuthenticatedUser())
                .build();

        taskRepository.save(task);
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateStatus(Long taskId, Status newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        task.setStatus(newStatus);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long taskId, TaskCreateUpdateRequest taskRequest) {
       Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setDueDate(taskRequest.getDueDate());
        task.setPriority(taskRequest.getPriority());
        taskRepository.save(task);
    }

    @Override
    public List<TaskResponse> getTasks(Status status, Priority priority) {
        if (status != null && priority != null) {
            return getTasksByStatusAndPriority(status, priority);
        } else if (status != null) {
            return getTasksByStatus(status);
        } else if (priority != null) {
            return getTasksByPriority(priority);
        } else {
            return getAllTasks();
        }
    }

    
    @Override
    public List<TaskResponse> getTask(Long taskId) {
        return taskRepository.findById(taskId)
                .map(task -> List.of(task.toTaskResponse()))
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }
    
    private List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(Task::toTaskResponse).toList();
    }

    private List<TaskResponse> getTasksByStatusAndPriority(Status status, Priority priority) {
        return taskRepository.findByStatusAndPriority(status, priority).stream().map(Task::toTaskResponse).toList();
    }

    private List<TaskResponse> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status).stream().map(Task::toTaskResponse).toList();
    }

    private List<TaskResponse> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority).stream().map(Task::toTaskResponse).toList();
    }



}
