package com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model;

import com.javidev.todo_list_spring_react_backend.presentation.controller.task.model.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDTO {
    private UUID id;
    private String title;
    private UUID userId;
    private List<TaskDTO> tasks;
}
