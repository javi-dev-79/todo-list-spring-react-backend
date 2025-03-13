package com.javidev.todo_list_spring_react_backend.presentation.controller.user.model;

import com.javidev.todo_list_spring_react_backend.presentation.controller.tasklist.model.TaskListDTO;
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
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String role;
    private List<TaskListDTO> taskLists;
}
