package com.javidev.todo_list_spring_react_backend.presentation.controller.task.mapper;

import com.javidev.todo_list_spring_react_backend.domain.model.task.CreateTaskParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.task.UpdateTaskParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.Task;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.CreateTaskRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.UpdateTaskRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "task.taskList.id", target = "taskListId")
    TaskDTO toDTO(Task task);

    List<TaskDTO> toDTO(List<Task> tasks);

    @Mapping(source = "taskListId", target = "taskListId")
    CreateTaskParameters toCreateTaskParameters(CreateTaskRequestBody requestBody);

    UpdateTaskParameters toUpdateTaskParameters(UpdateTaskRequestBody requestBody);
}
