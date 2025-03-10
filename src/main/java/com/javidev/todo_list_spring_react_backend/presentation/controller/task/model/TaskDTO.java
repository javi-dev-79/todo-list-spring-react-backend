package com.javidev.todo_list_spring_react_backend.presentation.controller.task.model;

import com.javidev.todo_list_spring_react_backend.persistence.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private UUID id;
    private String title;
    private String description;
    private Instant endDate;
    private TaskStatus taskStatus;
    private UUID taskListId;
}
