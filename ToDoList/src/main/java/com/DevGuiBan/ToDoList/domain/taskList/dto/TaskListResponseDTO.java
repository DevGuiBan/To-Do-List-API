package com.DevGuiBan.ToDoList.domain.taskList.dto;

import com.DevGuiBan.ToDoList.domain.task.Task;
import com.DevGuiBan.ToDoList.domain.taskList.TaskList;

import java.util.UUID;

public record TaskListResponseDTO(UUID id, String name, UUID taskId, String title, String description, boolean status) {
    public TaskListResponseDTO(TaskList taskList, Task task) {
        this(
                taskList.getId(),
                taskList.getName(),
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isStatus()
        );
    }
}
