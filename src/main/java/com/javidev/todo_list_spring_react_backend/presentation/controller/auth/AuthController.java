package com.javidev.todo_list_spring_react_backend.presentation.controller.auth;

import com.javidev.todo_list_spring_react_backend.domain.model.auth.LoginResponse;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.LoginRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequestBody credentials);

    @PostMapping("/register")
    RegisterResponse register(@RequestBody RegisterRequestBody request);

}