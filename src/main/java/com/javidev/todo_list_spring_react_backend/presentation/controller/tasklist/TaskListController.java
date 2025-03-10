package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist;

import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.CreateTaskListRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.TaskListDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.UpdateTaskListRequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/task_lists")
public interface TaskListController {

    @GetMapping
    List<TaskListDTO> getTaskLists();

    @GetMapping("/{id}")
    TaskListDTO getTaskListById(@PathVariable UUID id);

    @ResponseStatus(CREATED)
    @PostMapping
    TaskListDTO createTaskList(@RequestBody CreateTaskListRequestBody requestBody);

    @PutMapping("/{id}")
    TaskListDTO updateTaskListById(@PathVariable UUID id, @RequestBody UpdateTaskListRequestBody requestBody);

    @DeleteMapping("/{id}")
    void deleteTaskListById(@PathVariable UUID id);
}
