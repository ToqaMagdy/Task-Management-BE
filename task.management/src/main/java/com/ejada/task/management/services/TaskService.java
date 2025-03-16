package com.ejada.task.management.services;

import java.util.List;

import com.ejada.task.management.dto.task.TaskCreateUpdateRequest;
import com.ejada.task.management.dto.task.TaskResponse;
import com.ejada.task.management.enums.Priority;
import com.ejada.task.management.enums.Status;

public interface TaskService {

    public List<TaskResponse> getTasks(Status status, Priority priority);

    public void deleteTask(Long id);

    public void updateStatus(Long taskId, Status newStatus);

    public void createTask(TaskCreateUpdateRequest taskCreateRequest);

    public void updateTask(Long taskId, TaskCreateUpdateRequest taskRequest);

    public List<TaskResponse> getTask(Long taskId);

}
