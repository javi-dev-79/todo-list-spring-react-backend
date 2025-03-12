package com.javidev.todo_list_spring_react_backend.domain.service;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.exception.UnauthorizedAccessException;
import com.javidev.todo_list_spring_react_backend.domain.model.task.CreateTaskParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.task.UpdateTaskParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.Task;
import com.javidev.todo_list_spring_react_backend.persistence.model.TaskList;
import com.javidev.todo_list_spring_react_backend.persistence.repository.TaskListRepository;
import com.javidev.todo_list_spring_react_backend.persistence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Transactional(readOnly = true)
    public List<Task> getTasks(UUID userId, UUID taskListId) {
        var taskList = validateTaskListOwnership(userId, taskListId);
        return taskList.getTasks();
    }

    @Transactional(readOnly = true)
    public Task getTask(UUID userId, UUID taskListId, UUID taskId) {
        validateTaskListOwnership(userId, taskListId);
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NonExistingEntityException(Task.class, taskId));
    }

    @Transactional
    public Task createTask(UUID userId, UUID taskListId, CreateTaskParameters parameters) {
        if (taskListId == null) {
            throw new IllegalArgumentException("El ID de la lista de tareas no puede ser nulo");
        }

        var taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new NonExistingEntityException(TaskList.class, taskListId));

        if (!taskList.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("No tienes permisos para agregar tareas en esta lista.");
        }

        var task = Task.builder()
                .title(parameters.getTitle())
                .description(parameters.getDescription())
                .endDate(parameters.getEndDate())
                .taskStatus(parameters.getTaskStatus())
                .taskList(taskList)
                .build();

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(UUID userId, UUID taskListId, UUID taskId, UpdateTaskParameters parameters) {
        validateTaskListOwnership(userId, taskListId);
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NonExistingEntityException(Task.class, taskId));

        if (parameters.getTitle() != null) {
            task.setTitle(parameters.getTitle());
        }
        if (parameters.getDescription() != null) {
            task.setDescription(parameters.getDescription());
        }
        if (parameters.getEndDate() != null) {
            task.setEndDate(parameters.getEndDate());
        }
        if (parameters.getTaskStatus() != null) {
            task.setTaskStatus(parameters.getTaskStatus());
        }

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(UUID userId, UUID taskListId, UUID taskId) {
        validateTaskListOwnership(userId, taskListId);
        taskRepository.deleteById(taskId);
    }

    private TaskList validateTaskListOwnership(UUID userId, UUID taskListId) {
        var taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new NonExistingEntityException(TaskList.class, taskListId));

        if (!taskList.getUser().getId().equals(userId)) {
            throw new NonExistingEntityException(TaskList.class, taskListId);
        }
        return taskList;
    }
}