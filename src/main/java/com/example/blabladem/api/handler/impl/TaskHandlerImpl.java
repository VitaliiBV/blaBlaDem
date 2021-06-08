package com.example.blabladem.api.handler.impl;

import com.example.blabladem.api.handler.TaskHandler;
import com.example.blabladem.business.service.TaskService;
import com.example.blabladem.business.service.UserService;
import com.example.blabladem.domain.Comment;
import com.example.blabladem.domain.Task;
import com.example.blabladem.domain.TaskAttachment;
import com.example.blabladem.domain.TaskDetailsDTO;
import com.example.blabladem.dto.CommentDTO;
import com.example.blabladem.dto.TaskDTO;
import com.example.blabladem.dto.request.CreateTaskRequest;
import com.example.blabladem.dto.request.UpdateTaskRequest;
import com.example.blabladem.exception.BadRequestException;
import com.example.blabladem.thirdparty.impl.ExternalUserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskHandlerImpl implements TaskHandler {

    private final TaskService taskService;
    private final UserService userService;
    private final ExternalUserInfoServiceImpl externalUserInfoService;

    @Override
    public Page<TaskDTO> getAll(Long departmentId, Pageable pageable) {

        Page<Task> tasks = taskService.getAll(departmentId, pageable);

        Set<Long> userIds = new HashSet<>();

        //Fetching all unique userIds from tasks
        tasks.forEach(task -> {
            if (task.getAssignee() != null) {
                userIds.add(task.getAssignee().getId());
            }
            userIds.add(task.getAuthor().getId());
        });

        //Passing userIds to external service to get extraInfo(rating)
        Map<Long, Long> extraUserInfo = externalUserInfoService.getExtraUserInfo(userIds);

        Page<TaskDTO> taskDTOS = tasks.map(TaskDTO::fromEntity);

        //Populating UserDTOs with rating received from external service
        taskDTOS.forEach(taskDTO -> {
            if (taskDTO.getAssignee() != null) {
                Long rating = extraUserInfo.get(taskDTO.getAssignee().getId());
                if (rating!=null){
                    taskDTO.getAssignee().setRating(rating);
                }
            }
            Long rating = extraUserInfo.get(taskDTO.getAuthor().getId());
            if (rating!=null){
                taskDTO.getAuthor().setRating(rating);
            };
        });

        return taskDTOS;
    }

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
    @Transactional
    public void addAttachment(Long taskId, MultipartFile multipartFile) {
        Task task = taskService.getById(taskId);
        try {
            byte[] fileBytes = multipartFile.getBytes();
            TaskAttachment attachment = TaskAttachment.builder()
                    .task(task)
                    .file(fileBytes)
                    .build();
            task.getAttachments().add(attachment);
            taskService.update(task);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BadRequestException("Exception during file processing");
        }
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
