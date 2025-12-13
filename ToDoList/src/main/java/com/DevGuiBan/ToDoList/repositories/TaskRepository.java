package com.DevGuiBan.ToDoList.repositories;

import com.DevGuiBan.ToDoList.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
