package com.javidev.todo_list_spring_react_backend.domain.model.task;

import com.javidev.todo_list_spring_react_backend.persistence.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateTaskParameters {
    private String title;
    private String description;
    private Instant endDate;
    private TaskStatus taskStatus;
}
