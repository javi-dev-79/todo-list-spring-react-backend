package com.javidev.todo_list_spring_react_backend.domain.model.tasklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateTaskListParameters {
    private String name;
}
