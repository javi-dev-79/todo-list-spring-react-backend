package com.javidev.todo_list_spring_react_backend.domain.service;


import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.model.tasklist.CreateTaskListParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.tasklist.UpdateTaskListParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.persistence.model.TaskList;
import com.javidev.todo_list_spring_react_backend.persistence.repository.TaskListRepository;
import com.javidev.todo_list_spring_react_backend.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TaskList> getUserTaskLists(UUID userId) {
        return taskListRepository.findAllByUserId(userId);
    }

    @Transactional
    public TaskList createTaskList(UUID userId, CreateTaskListParameters parameters) {
        if (userId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NonExistingEntityException(AppUser.class, userId));

        var taskList = TaskList.builder()
                .name(parameters.getName())
                .user(user)
                .build();

        return taskListRepository.save(taskList);
    }


    @Transactional
    public TaskList updateTaskList(UUID userId, UUID taskListId, UpdateTaskListParameters parameters) {
        var taskList = validateTaskListOwnership(userId, taskListId);

        if (parameters.getName() != null) {
            taskList.setName(parameters.getName());
        }

        return taskListRepository.save(taskList);
    }

    @Transactional
    public void deleteTaskList(UUID userId, UUID taskListId) {
        validateTaskListOwnership(userId, taskListId);
        taskListRepository.deleteById(taskListId);
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