package com.javidev.todo_list_spring_react_backend.presentation.controller.user.impl;

import com.javidev.todo_list_spring_react_backend.domain.service.UserService;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.UserController;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.mapper.UserMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.CreateUserRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UpdateUserRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UpdateUserRoleRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getUsers() {
        return userMapper.toDTO(userService.getAllUsers());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(userService.getUserById(id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        System.out.println("🔹 Buscando usuario con email: " + email);
        return userMapper.toDTO(userService.getUserByEmail(email));
    }

    @Override
    public UserDTO createUser(CreateUserRequestBody requestBody) {
        return userMapper.toDTO(userService.createUser(userMapper.toCreateUserParameters(requestBody)));
    }

    @Override
    public UserDTO updateUser(UUID id, UpdateUserRequestBody requestBody) {
        return userMapper.toDTO(userService.updateUser(id, userMapper.toUpdateUserParameters(requestBody)));
    }

    @Override
    public UserDTO updateUserRole(UUID id, UpdateUserRoleRequestBody requestBody) {
        return userMapper.toDTO((userService.updateUserRole(id, requestBody.getRole())));
    }

    @Override
    public void deleteUser(UUID id) {
        userService.deleteUser(id);
    }

}
