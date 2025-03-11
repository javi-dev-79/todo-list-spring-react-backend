package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.mapper;

import com.javidev.todo_list_spring_react_backend.domain.model.tasklist.CreateTaskListParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.tasklist.UpdateTaskListParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.TaskList;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.CreateTaskListRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.TaskListDTO;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.UpdateTaskListRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "tasks", target = "tasks")
    TaskListDTO toDTO(TaskList taskList);

    List<TaskListDTO> toDTO(List<TaskList> taskLists);

    //    @Mapping(source = "userId", target = "userId")
    CreateTaskListParameters toCreateTaskListParameters(CreateTaskListRequestBody requestBody);

    UpdateTaskListParameters toUpdateTaskListParameters(UpdateTaskListRequestBody requestBody);
}
