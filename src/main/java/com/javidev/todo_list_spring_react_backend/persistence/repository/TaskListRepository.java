package com.javidev.todo_list_spring_react_backend.persistence.repository;

import com.javidev.todo_list_spring_react_backend.persistence.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
    List<TaskList> findAllByUserId(UUID userId);
}
