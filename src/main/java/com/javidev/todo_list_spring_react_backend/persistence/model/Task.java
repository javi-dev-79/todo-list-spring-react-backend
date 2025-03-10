package com.javidev.todo_list_spring_react_backend.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @UuidGenerator
    private UUID id;

    private String title;
    private String description;
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "task_list_id", nullable = false)
    private TaskList taskList;

}
