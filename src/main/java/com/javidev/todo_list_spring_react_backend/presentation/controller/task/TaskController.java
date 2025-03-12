package com.javidev.todo_list_spring_react_backend.presentation.controller.task;

import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.CreateTaskRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.UpdateTaskRequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/users/{userId}/tasklists/{taskListId}/tasks")
public interface TaskController {

    @GetMapping
    List<TaskDTO> getTasks(@PathVariable UUID userId, @PathVariable UUID taskListId);

    @GetMapping("/{taskId}")
    TaskDTO getTask(@PathVariable UUID userId, @PathVariable UUID taskListId, @PathVariable UUID taskId);

    @ResponseStatus(CREATED)
    @PostMapping
    TaskDTO createTask(@PathVariable UUID userId, @PathVariable UUID taskListId, @RequestBody CreateTaskRequestBody requestBody);

    @PutMapping("/{taskId}")
    TaskDTO updateTask(@PathVariable UUID userId, @PathVariable UUID taskListId, @PathVariable UUID taskId, @RequestBody UpdateTaskRequestBody requestBody);

    @DeleteMapping("/{taskId}")
    void deleteTask(@PathVariable UUID userId, @PathVariable UUID taskListId, @PathVariable UUID taskId);
}
