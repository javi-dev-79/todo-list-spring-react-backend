package com.javidev.todo_list_spring_react_backend.persistence.repository;

import com.javidev.todo_list_spring_react_backend.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
