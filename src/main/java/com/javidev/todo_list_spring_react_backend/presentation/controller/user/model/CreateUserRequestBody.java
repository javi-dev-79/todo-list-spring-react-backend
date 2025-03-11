package com.javidev.todo_list_spring_react_backend.presentation.controller.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserRequestBody {
    private String name;
    private String email;
}
