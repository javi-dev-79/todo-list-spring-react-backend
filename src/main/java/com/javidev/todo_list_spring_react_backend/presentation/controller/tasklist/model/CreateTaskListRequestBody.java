package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskListRequestBody {
    private String name;
    private UUID userId;
}
