package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist;

import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.CreateTaskListRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.TaskListDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.UpdateTaskListRequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/users/{userId}/tasklists")
public interface TaskListController {

    @GetMapping
    List<TaskListDTO> getUserTaskLists(@PathVariable UUID userId);

    @ResponseStatus(CREATED)
    @PostMapping
    TaskListDTO createTaskList(@PathVariable UUID userId, @RequestBody CreateTaskListRequestBody requestBody);

    @PutMapping("/{taskListId}")
    TaskListDTO updateTaskList(@PathVariable UUID userId, @PathVariable UUID taskListId, @RequestBody UpdateTaskListRequestBody requestBody);

    @DeleteMapping("/{taskListId}")
    void deleteTaskList(@PathVariable UUID userId, @PathVariable UUID taskListId);
}

