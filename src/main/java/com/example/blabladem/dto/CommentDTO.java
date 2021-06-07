package com.example.blabladem.dto;

import com.example.blabladem.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String comment;
    private Long taskId;

    public static CommentDTO fromEntity(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDTO.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .taskId(comment.getTask().getId())
                .build();
    }
}
