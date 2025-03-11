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

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NonExistingEntityException(Task.class, id));
    }

    @Transactional
    public Task createTask(CreateTaskParameters parameters) {
        if (parameters.getTaskListId() == null) {
            throw new IllegalArgumentException("El ID de la lista de tareas no puede ser nulo");
        }

        TaskList taskList = taskListRepository.findById(parameters.getTaskListId())
                .orElseThrow(() -> new NonExistingEntityException(TaskList.class, parameters.getTaskListId()));

        Task task = Task.builder()
                .title(parameters.getTitle())
                .description(parameters.getDescription())
                .endDate(parameters.getEndDate())
                .taskStatus(parameters.getTaskStatus())
                .taskList(taskList)
                .build();

        return taskRepository.save(task);
    }


    @Transactional
    public Task updateTask(UUID id, UpdateTaskParameters updateTaskParameters) {

        var existingTask = getTaskById(id);

        if (updateTaskParameters.getTitle() != null) {
            existingTask.setTitle(updateTaskParameters.getTitle());
        }
        if (updateTaskParameters.getDescription() != null) {
            existingTask.setDescription(updateTaskParameters.getDescription());
        }
        if (updateTaskParameters.getEndDate() != null) {
            existingTask.setEndDate(updateTaskParameters.getEndDate());
        }
        if (updateTaskParameters.getTaskStatus() != null) {
            existingTask.setTaskStatus(updateTaskParameters.getTaskStatus());
        }

        return taskRepository.save(existingTask);
    }

    @Transactional
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
