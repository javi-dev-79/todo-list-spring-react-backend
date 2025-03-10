package com.javidev.todo_list_spring_react_backend.presentation.controller.task.model;

import com.javidev.todo_list_spring_react_backend.persistence.model.TaskList;
import com.javidev.todo_list_spring_react_backend.persistence.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateTaskRequestBody {
    private String title;
    private String description;
    private Instant end_date;
    private TaskStatus taskStatus;
    private TaskList taskList;
}
