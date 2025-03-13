package com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterResponse {
    private String message;
    private String email;
    private String token;
}
