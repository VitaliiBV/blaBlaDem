package com.example.blabladem.repository;

import com.example.blabladem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteCommentByIdAndTask_id(Long id, Long taskId);
}
