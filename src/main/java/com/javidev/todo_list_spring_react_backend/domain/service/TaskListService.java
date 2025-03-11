package com.javidev.todo_list_spring_react_backend.domain.service;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.model.tasklist.CreateTaskListParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.tasklist.UpdateTaskListParameters;
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

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public TaskList getTaskListById(UUID id) {
        return taskListRepository.findById(id).orElseThrow(() -> new NonExistingEntityException(TaskList.class, id));
    }

    @Transactional
    public TaskList createTaskList(CreateTaskListParameters parameters) {
        var user = userRepository.findById(parameters.getUserId())
                .orElseThrow(() -> new NonExistingEntityException(TaskList.class, parameters.getUserId()));

        var taskList = TaskList.builder()
                .name(parameters.getName())
                .user(user)
                .tasks(List.of())
                .build();

        return taskListRepository.save(taskList);
    }

    @Transactional
    public TaskList updateTaskList(UUID id, UpdateTaskListParameters updateTaskListParameters) {
        var existingTaskList = getTaskListById(id);
        if (updateTaskListParameters.getName() != null) {
            existingTaskList.setName(updateTaskListParameters.getName());
        }
        return taskListRepository.save(existingTaskList);
    }

    @Transactional
    public void deleteTaskList(UUID id) {
        taskListRepository.deleteById(id);
    }
}
