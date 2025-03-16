package com.ejada.task.management.dto.task;

import java.util.Date;

import com.ejada.task.management.enums.Priority;
import com.ejada.task.management.enums.Status;

import lombok.Data;

@Data
public class TaskResponse {
    
    private Long id;

    private String title;
    
    private String description;
        
    private Status status;
    
    private Date dueDate;

    private Priority priority;
    
    private String createdBy;
}
