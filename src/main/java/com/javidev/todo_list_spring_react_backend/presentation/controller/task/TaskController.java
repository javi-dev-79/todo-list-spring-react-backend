package com.javidev.todo_list_spring_react_backend.presentation.controller.task;

import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.CreateTaskRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.UpdateTaskRequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/tasks")
public interface TaskController {

    @GetMapping
    List<TaskDTO> getTasks();

    @GetMapping("/{id}")
    TaskDTO getTask(@PathVariable UUID id);

    @ResponseStatus(CREATED)
    @PostMapping
    TaskDTO createTask(@RequestBody CreateTaskRequestBody requestBody);

    @PutMapping("/{id}")
    TaskDTO updateTask(@PathVariable UUID id, @RequestBody UpdateTaskRequestBody requestBody);

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable UUID id);
}
