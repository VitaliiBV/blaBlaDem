package com.example.blabladem.business.service;

import com.example.blabladem.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task getById(Long id);

    Task create(Task task);

    Task update(Task task);

    byte[] getAttachmentByTaskIdAndId(Long taskId, Long attachmentId);

    void deleteCommentByIdAndTaskId(Long id, Long taskId);

    Page<Task> getAll(Long departmentId, Pageable pageable);
}
