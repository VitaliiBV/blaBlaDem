package com.example.blabladem.dto.request;

import com.example.blabladem.domain.TaskStatus;
import lombok.Data;

@Data
public class UpdateTaskRequest {
    private TaskStatus status;
    private Long assigneeId;
}
