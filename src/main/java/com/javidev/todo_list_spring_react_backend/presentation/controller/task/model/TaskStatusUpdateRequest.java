package com.javidev.todo_list_spring_react_backend.presentation.controller.task.model;

import com.javidev.todo_list_spring_react_backend.persistence.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskStatusUpdateRequest {
    private TaskStatus taskStatus;
}
