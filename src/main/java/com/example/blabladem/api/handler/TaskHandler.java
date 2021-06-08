package com.example.blabladem.api.handler;

import com.example.blabladem.domain.TaskDetailsDTO;
import com.example.blabladem.dto.CommentDTO;
import com.example.blabladem.dto.TaskDTO;
import com.example.blabladem.dto.request.CreateTaskRequest;
import com.example.blabladem.dto.request.UpdateTaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface TaskHandler {
    Page<TaskDTO> getAll(Long departmentId, Pageable pageable);

    TaskDTO createTask(CreateTaskRequest taskDTO);

    TaskDetailsDTO getById(Long id);

    byte[] getTaskAttachment(Long taskId, Long attachmentId);

    TaskDTO update(Long taskId, UpdateTaskRequest request);

    void addComment(Long taskId, CommentDTO commentDTO);

    void deleteComment(Long commentId, Long taskId);

    void addAttachment(Long taskId, MultipartFile multipartFile);
}
