package com.example.blabladem.web;

import com.example.blabladem.api.handler.TaskHandler;
import com.example.blabladem.domain.TaskDetailsDTO;
import com.example.blabladem.dto.CommentDTO;
import com.example.blabladem.dto.TaskDTO;
import com.example.blabladem.dto.request.CreateTaskRequest;
import com.example.blabladem.dto.request.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(TaskController.Path.BASE)
public class TaskController {

    private final TaskHandler taskHandler;

    public interface Path {
        String BASE = "/task";
        String CREATE = "/create";
        String UPDATE = "/update/{id}";
        String ADD_COMMENT = "/{taskId}/addComment";
        String DELETE_COMMENT = "/deleteComment/{taskId}/{commentId}";
        String TASK_BY_ID = "/{id}";
        String ATTACHMENT_BY_TASK_ID_AND_ID = "/{taskId}/{attachmentId}";
    }

    @GetMapping
    public Page<TaskDTO> getAllTasks(@RequestParam(value = "departmentId") Long departmentId, Pageable pageable){
        return taskHandler.getAll(departmentId, pageable);
    }

    @PostMapping(Path.CREATE)
    public TaskDTO createTask(@RequestBody CreateTaskRequest createTaskRequest){
        return taskHandler.createTask(createTaskRequest);
    }

    @GetMapping(Path.TASK_BY_ID)
    public TaskDetailsDTO getTaskDetails(@PathVariable Long id){
        return taskHandler.getById(id);
    }

    @GetMapping(value=Path.ATTACHMENT_BY_TASK_ID_AND_ID, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getAttachment(@PathVariable Long taskId, @PathVariable Long attachmentId){
        return taskHandler.getTaskAttachment(taskId, attachmentId);
    }

    @PutMapping(Path.UPDATE)
    public TaskDTO update(@PathVariable Long id, @RequestBody UpdateTaskRequest request){
        return taskHandler.update(id, request);
    }

    @PostMapping(Path.ADD_COMMENT)
    public void addComment(@PathVariable Long taskId, @RequestBody CommentDTO commentDTO){
        taskHandler.addComment(taskId, commentDTO);
    }

    @DeleteMapping(Path.DELETE_COMMENT)
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long taskId){
        taskHandler.deleteComment(commentId, taskId);
    }
}