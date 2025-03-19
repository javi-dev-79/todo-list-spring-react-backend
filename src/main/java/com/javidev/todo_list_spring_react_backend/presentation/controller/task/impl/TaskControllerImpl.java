package com.javidev.todo_list_spring_react_backend.presentation.controller.task.impl;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.service.TaskService;
import com.javidev.todo_list_spring_react_backend.persistence.model.Task;
import com.javidev.todo_list_spring_react_backend.persistence.repository.TaskRepository;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.TaskController;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.mapper.TaskMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.CreateTaskRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskStatusUpdateRequest;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.UpdateTaskRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getTasks(UUID userId, UUID taskListId) {
        return taskMapper.toDTO(taskService.getTasks(userId, taskListId));
    }

    @Override
    public TaskDTO getTask(UUID userId, UUID taskListId, UUID taskId) {
        return taskMapper.toDTO(taskService.getTask(userId, taskListId, taskId));
    }

    @Override
    public TaskDTO createTask(@PathVariable UUID userId, @PathVariable UUID taskListId, @RequestBody CreateTaskRequestBody requestBody) {
        return taskMapper.toDTO(taskService.createTask(userId, taskListId, taskMapper.toCreateTaskParameters(requestBody)));
    }


    @Override
    public TaskDTO updateTask(
            @PathVariable UUID userId,
            @PathVariable UUID taskListId,
            @PathVariable UUID taskId,
            @RequestBody UpdateTaskRequestBody requestBody
    ) {
        return taskMapper.toDTO(taskService.updateTask(userId, taskListId, taskId, taskMapper.toUpdateTaskParameters(requestBody)));
    }

    @Override
    public TaskDTO updateTaskStatus(UUID userId, UUID taskListId, UUID taskId, TaskStatusUpdateRequest request) {
        Task task = taskRepository.findByIdAndTaskListIdAndTaskListUserId(taskId, taskListId, userId)
                .orElseThrow(() -> new NonExistingEntityException(Task.class));

        task.setTaskStatus(request.getTaskStatus());
        return taskMapper.toDTO(taskRepository.save(task));
    }


    @Override
    public void deleteTask(UUID userId, UUID taskListId, UUID taskId) {
        taskService.deleteTask(userId, taskListId, taskId);
    }
}
