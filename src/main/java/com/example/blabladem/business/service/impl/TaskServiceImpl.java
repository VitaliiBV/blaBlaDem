package com.example.blabladem.business.service.impl;

import com.example.blabladem.repository.CommentRepository;
import com.example.blabladem.repository.TaskAttachmentRepository;
import com.example.blabladem.repository.TaskRepository;
import com.example.blabladem.business.service.TaskService;
import com.example.blabladem.domain.Task;
import com.example.blabladem.domain.TaskAttachment;
import com.example.blabladem.exception.BadRequestException;
import com.example.blabladem.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskAttachmentRepository taskAttachmentRepository;
    private final CommentRepository commentRepository;

    public Page<Task> getAll(Long departmentId, Pageable pageable){
        return taskRepository.findAllByAssignee_Department_Id(departmentId, pageable);
    }

    @Override
    public Task getById(Long id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task doest not exist!"));
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public byte[] getAttachmentByTaskIdAndId(Long taskId, Long attachmentId){
        TaskAttachment attachment = taskAttachmentRepository.findTaskAttachmentByTask_IdAndId(taskId, attachmentId).orElseThrow(() -> new BadRequestException("Such attachment does not exist"));
        return attachment.getFile();
    }

    @Override
    public void deleteCommentByIdAndTaskId(Long id, Long taskId){
        commentRepository.deleteCommentByIdAndTask_id(id, taskId);
    }
}
