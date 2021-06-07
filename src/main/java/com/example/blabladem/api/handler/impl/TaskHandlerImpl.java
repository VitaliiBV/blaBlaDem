package com.example.blabladem.api.handler.impl;

import com.example.blabladem.api.handler.TaskHandler;
import com.example.blabladem.api.service.TaskService;
import com.example.blabladem.api.service.UserService;
import com.example.blabladem.domain.Comment;
import com.example.blabladem.domain.Task;
import com.example.blabladem.domain.TaskDetailsDTO;
import com.example.blabladem.dto.CommentDTO;
import com.example.blabladem.dto.TaskDTO;
import com.example.blabladem.dto.request.CreateTaskRequest;
import com.example.blabladem.dto.request.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class TaskHandlerImpl implements TaskHandler {

    private final TaskService taskService;
    private final UserService userService;
//    private final ExternalUserInfoService externalUserInfoService;

    @Override
    @Transactional
    public TaskDetailsDTO getById(Long id) {
        return TaskDetailsDTO.fromEntity(taskService.getById(id));
    }

    @Override
    @Transactional
    public byte[] getTaskAttachment(Long taskId, Long attachmentId) {
        return taskService.getAttachmentByTaskIdAndId(taskId, attachmentId);
    }

    @Override
    @Transactional
    public TaskDTO update(Long taskId, UpdateTaskRequest request) {
        Task task = taskService.getById(taskId);
        task.setStatus(request.getStatus());
        task.setAssignee(userService.getById(request.getAssigneeId()));
        return TaskDTO.fromEntity(taskService.update(task));

    }

    @Override
    @Transactional
    public void addComment(Long taskId, CommentDTO commentDTO) {
        Task task = taskService.getById(taskId);
        Comment comment = Comment.builder()
                .comment(commentDTO.getComment())
                .task(task)
                .build();
        task.getComments().add(comment);
        taskService.update(task);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long taskId) {
        taskService.deleteCommentByIdAndTaskId(commentId, taskId);
    }

    @Override
    public Page<TaskDTO> getAll(Long departmentId, Pageable pageable) {
        return taskService.getAll(departmentId, pageable).map(TaskDTO::fromEntity);
    }

    @Override
    @Transactional
    public TaskDTO createTask(CreateTaskRequest createTaskRequest) {
        Long assigneeId = createTaskRequest.getAssigneeId();
        Task task = Task.builder()
                .topic(createTaskRequest.getTopic())
                .status(createTaskRequest.getStatus())
                .author(userService.getById(createTaskRequest.getAuthorId()))
                .assignee(assigneeId == null ? null : userService.getById(assigneeId))
                .build();
        return TaskDTO.fromEntity(taskService.create(task));
    }
}
