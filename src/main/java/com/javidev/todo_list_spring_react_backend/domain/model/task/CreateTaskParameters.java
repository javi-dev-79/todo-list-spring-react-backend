package com.javidev.todo_list_spring_react_backend.domain.model.task;

import com.javidev.todo_list_spring_react_backend.persistence.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateTaskParameters {
    private String title;
    private String description;
    private Instant endDate;
    private TaskStatus taskStatus;
    private UUID taskListId;
}
