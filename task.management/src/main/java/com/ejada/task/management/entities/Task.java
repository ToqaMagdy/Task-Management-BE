package com.ejada.task.management.entities;

import java.util.Date;

import com.ejada.task.management.dto.task.TaskResponse;
import com.ejada.task.management.enums.Priority;
import com.ejada.task.management.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_sequence", allocationSize = 1)
    private Long id;


    @Column(nullable = false)
    private String title;
    
    private String description;
        
    @Enumerated(EnumType.STRING)
    private Status status;
    
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    
    @ManyToOne
    private User createdBy;

    public TaskResponse toTaskResponse() {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(this.id);
        taskResponse.setTitle(this.title);
        taskResponse.setDescription(this.description);
        taskResponse.setStatus(this.status);
        taskResponse.setDueDate(this.dueDate);
        taskResponse.setPriority(this.priority);
        taskResponse.setCreatedBy(this.createdBy.getUsername());
        return taskResponse;
    }
}
