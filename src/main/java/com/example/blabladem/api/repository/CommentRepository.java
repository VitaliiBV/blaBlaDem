package com.example.blabladem.api.repository;

import com.example.blabladem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteCommentByIdAndTask_id(Long id, Long taskId);
}
