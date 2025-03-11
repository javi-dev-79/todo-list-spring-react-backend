package com.javidev.todo_list_spring_react_backend.domain.model.tasklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateTaskListParameters {
    private String name;
    private UUID userId;
}
