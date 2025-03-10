package com.javidev.todo_list_spring_react_backend.domain.model.tasklist;

import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateTaskListParameters {
    private String name;
    private AppUser user;
}
