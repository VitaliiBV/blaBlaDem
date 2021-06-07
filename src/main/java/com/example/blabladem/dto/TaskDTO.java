package com.example.blabladem.dto;

import com.example.blabladem.domain.Task;
import com.example.blabladem.domain.TaskStatus;
import com.example.blabladem.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private Long date;

    private String topic;

    @NotNull
    private UserDTO assignee;

    private UserDTO author;

    private TaskStatus status;

    public static TaskDTO fromEntity(Task task){
        if (task==null){
            return null;
        }
        return TaskDTO.builder()
                .id(task.getId())
                .status(task.getStatus())
                .topic(task.getTopic())
                .assignee(UserDTO.fromEntity(task.getAssignee()))
                .author(UserDTO.fromEntity(task.getAuthor()))
                .date(task.getCreationDate().toEpochMilli())
                .build();
    }
}
