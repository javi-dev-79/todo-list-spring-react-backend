package com.javidev.todo_list_spring_react_backend.presentation.controller.auth.impl;

import com.javidev.todo_list_spring_react_backend.domain.model.auth.LoginResponse;
import com.javidev.todo_list_spring_react_backend.domain.service.AuthService;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.AuthController;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.mapper.AuthMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.LoginRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final AuthMapper authMapper;


    @Override
    public LoginResponse login(LoginRequestBody credentials) {
        return authService.login(credentials);
    }

    @Override
    public RegisterResponse register(RegisterRequestBody request) {
        return authService.register(request);
    }

}