package com.javidev.todo_list_spring_react_backend.presentation.controller.user.mapper;

import com.javidev.todo_list_spring_react_backend.domain.model.user.CreateUserParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.user.UpdateUserParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.mapper.TaskListMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.CreateUserRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UpdateUserRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskListMapper.class)
public interface UserMapper {

    @Mapping(target = "taskLists", source = "taskLists", defaultExpression = "java(new ArrayList<>())")
    UserDTO toDTO(AppUser user);

    List<UserDTO> toDTO(List<AppUser> users);

    CreateUserParameters toCreateUserParameters(CreateUserRequestBody requestBody);

    UpdateUserParameters toUpdateUserParameters(UpdateUserRequestBody requestBody);
}
