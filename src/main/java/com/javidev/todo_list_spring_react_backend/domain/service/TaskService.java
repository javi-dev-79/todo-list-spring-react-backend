package com.javidev.todo_list_spring_react_backend.domain.service;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.model.task.CreateTaskParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.task.UpdateTaskParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.Task;
import com.javidev.todo_list_spring_react_backend.persistence.model.TaskList;
import com.javidev.todo_list_spring_react_backend.persistence.repository.TaskListRepository;
import com.javidev.todo_list_spring_react_backend.persistence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
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
    public List<Task> getTasks(@NonNull UUID userId, @NonNull UUID taskListId) {
        validateTaskListOwnership(userId, taskListId);
        var taskList = taskListRepository.findByIdAndUserId(taskListId, userId).orElseThrow(() -> new NonExistingEntityException(TaskList.class, taskListId));
        return taskList.getTasks();
    }

    @Transactional(readOnly = true)
    public Task getTask(@NonNull UUID userId, @NonNull UUID taskListId, @NonNull UUID taskId) {
        validateTaskListOwnership(userId, taskListId);
        return taskRepository.findByIdAndTaskListId(taskId, taskListId)
                .orElseThrow(() -> new NonExistingEntityException(Task.class, taskId));
    }

    @Transactional
    public Task createTask(@NonNull UUID userId, @NonNull UUID taskListId, @NonNull CreateTaskParameters parameters) {

        validateTaskListOwnership(userId, taskListId);

        var taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new NonExistingEntityException(TaskList.class, taskListId));

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
    public Task updateTask(@NonNull UUID userId, @NonNull UUID taskListId, @NonNull UUID taskId, @NonNull UpdateTaskParameters parameters) {
        var task = getTask(userId, taskListId, taskId);

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
    public void deleteTask(@NonNull UUID userId, @NonNull UUID taskListId, @NonNull UUID taskId) {
        getTask(userId, taskListId, taskId);
        taskRepository.deleteById(taskId);
    }

    private void validateTaskListOwnership(UUID userId, UUID taskListId) {
        taskListRepository.findByIdAndUserId(taskListId, userId).orElseThrow(() -> new NonExistingEntityException(TaskList.class, taskListId));
    }
}