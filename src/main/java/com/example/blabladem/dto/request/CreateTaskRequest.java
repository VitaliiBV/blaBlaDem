package com.example.blabladem.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateTaskRequest extends UpdateTaskRequest{
    private String topic;
    private Long authorId;

}
