package com.javidev.todo_list_spring_react_backend.presentation.controller.user;

import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.CreateUserRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UpdateUserRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UpdateUserRoleRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/users")
public interface UserController {

    @GetMapping
    List<UserDTO> getUsers();

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable UUID id);

    @ResponseStatus(CREATED)
    @PostMapping
    UserDTO createUser(
            @RequestBody CreateUserRequestBody requestBody
    );

    @PutMapping("/{id}")
    UserDTO updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequestBody requestBody
    );

    @PutMapping("/{id}/role")
    UserDTO updateUserRole(
            @PathVariable UUID id,
            @RequestBody UpdateUserRoleRequestBody requestBody
    );

    @DeleteMapping("/{id}")
    void deleteUser(
            @PathVariable UUID id
    );

}
