package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.impl;

import com.javidev.todo_list_spring_react_backend.domain.service.TaskListService;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.TaskListController;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.mapper.TaskListMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.CreateTaskListRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.TaskListDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.UpdateTaskListRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TaskListControllerImpl implements TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @Override
    public List<TaskListDTO> getUserTaskLists(UUID userId) {
        return taskListMapper.toDTO(taskListService.getUserTaskLists(userId));
    }

    @Override
    public TaskListDTO createTaskList(@PathVariable UUID userId, @RequestBody CreateTaskListRequestBody requestBody) {
        return taskListMapper.toDTO(taskListService.createTaskList(userId, taskListMapper.toCreateTaskListParameters(requestBody)));
    }

    @Override
    public TaskListDTO updateTaskList(@PathVariable UUID userId, @PathVariable UUID taskListId, @RequestBody UpdateTaskListRequestBody requestBody) {
        return taskListMapper.toDTO(taskListService.updateTaskList(userId, taskListId, taskListMapper.toUpdateTaskListParameters(requestBody)));
    }

    @Override
    public void deleteTaskList(UUID userId, UUID taskListId) {
        taskListService.deleteTaskList(userId, taskListId);
    }
}
