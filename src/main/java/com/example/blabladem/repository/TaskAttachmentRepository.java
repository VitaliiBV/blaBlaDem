package com.example.blabladem.repository;

import com.example.blabladem.domain.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {
    Optional<TaskAttachment> findTaskAttachmentByTask_IdAndId(Long taskId, Long attachmentId);
}
