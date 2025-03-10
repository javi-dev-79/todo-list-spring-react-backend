package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.impl;

import com.javidev.todo_list_spring_react_backend.domain.service.TaskListService;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.TaskListController;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.mapper.TaskListMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.CreateTaskListRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.TaskListDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.UpdateTaskListRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TaskListControllerImpl implements TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @Override
    public List<TaskListDTO> getTaskLists() {
        return taskListMapper.toDTO(taskListService.getAllTaskLists());
    }

    @Override
    public TaskListDTO getTaskListById(UUID id) {
        return taskListMapper.toDTO(taskListService.getTaskListById(id));
    }

    @Override
    public TaskListDTO createTaskList(CreateTaskListRequestBody requestBody) {
        return taskListMapper.toDTO(taskListService.createTaskList(taskListMapper.toCreateTaskListParameters(requestBody)));
    }

    @Override
    public TaskListDTO updateTaskListById(UUID id, UpdateTaskListRequestBody requestBody) {
        return taskListMapper.toDTO(taskListService.updateTaskList(id, taskListMapper.toUpdateTaskListParameters(requestBody)));
    }

    @Override
    public void deleteTaskListById(UUID id) {
        taskListService.deleteTaskList(id);
    }
}
