package com.example.blabladem.dto;

import com.example.blabladem.domain.TaskAttachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAttachmentDTO {
    private Long id;
    private Long taskId;

    public static TaskAttachmentDTO fromEntity(TaskAttachment attachment){
        if (attachment==null){
            return null;
        }
        return TaskAttachmentDTO.builder()
                .id(attachment.getId())
                .taskId(attachment.getTask().getId())
                .build();
    }
}
