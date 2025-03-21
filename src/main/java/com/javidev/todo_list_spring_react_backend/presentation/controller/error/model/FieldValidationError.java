package com.javidev.todo_list_spring_react_backend.presentation.controller.error.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidationError {
    private String field;
    private List<String> errors;
}
