package com.example.blabladem.domain;

import com.example.blabladem.dto.CommentDTO;
import com.example.blabladem.dto.TaskAttachmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailsDTO {

    private Long taskId;

    private List<CommentDTO> comments;
    private List<Long> attachmentIds;

    public static TaskDetailsDTO fromEntity(Task task){
        return TaskDetailsDTO.builder()
                .taskId(task.getId())
                .comments(task.getComments().stream().map(CommentDTO::fromEntity).collect(Collectors.toList()))
                .attachmentIds(task.getAttachments().stream().map(TaskAttachment::getId).collect(Collectors.toList()))
                .build();
    }
}
