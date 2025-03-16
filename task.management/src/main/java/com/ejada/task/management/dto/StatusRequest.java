package com.ejada.task.management.dto;


import com.ejada.task.management.enums.Status;

import lombok.Data;

@Data
public class StatusRequest {
    private Status status;
}
