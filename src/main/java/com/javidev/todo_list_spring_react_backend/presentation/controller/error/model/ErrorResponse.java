package com.javidev.todo_list_spring_react_backend.presentation.controller.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ErrorResponse {
    private int code;
    private String message;
}
