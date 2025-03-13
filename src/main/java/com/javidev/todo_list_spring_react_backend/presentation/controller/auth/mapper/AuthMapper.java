package com.javidev.todo_list_spring_react_backend.presentation.controller.auth.mapper;

import com.javidev.todo_list_spring_react_backend.domain.model.auth.LoginRequestParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.auth.RegisterRequestParameters;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.LoginRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    RegisterRequestParameters toRegisterRequestParameters(RegisterRequestBody requestBody);

    LoginRequestParameters toLoginRequestParameters(LoginRequestBody requestBody);

    RegisterResponse toRegisterResponse(String message, String email, String token);
}
