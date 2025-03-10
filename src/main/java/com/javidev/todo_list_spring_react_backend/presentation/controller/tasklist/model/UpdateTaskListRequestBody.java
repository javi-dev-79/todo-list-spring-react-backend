package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskListRequestBody {
    private String name;
}
