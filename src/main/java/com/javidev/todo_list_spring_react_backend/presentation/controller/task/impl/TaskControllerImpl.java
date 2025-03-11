package com.javidev.todo_list_spring_react_backend.presentation.controller.task.impl;

import com.javidev.todo_list_spring_react_backend.domain.service.TaskService;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.TaskController;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.mapper.TaskMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.CreateTaskRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.UpdateTaskRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDTO> getTasks() {
        return taskMapper.toDTO(taskService.getAllTasks());
    }

    @Override
    public TaskDTO getTask(UUID id) {
        return taskMapper.toDTO(taskService.getTaskById(id));
    }

    @Override
    public TaskDTO createTask(CreateTaskRequestBody requestBody) {
        return taskMapper.toDTO(taskService.createTask(taskMapper.toCreateTaskParameters(requestBody)));
    }

    @Override
    public TaskDTO updateTask(UUID id, UpdateTaskRequestBody requestBody) {
        return taskMapper.toDTO(taskService.updateTask(id, taskMapper.toUpdateTaskParameters(requestBody)));
    }

    @Override
    public void deleteTask(UUID id) {
        taskService.deleteTask(id);
    }
}
