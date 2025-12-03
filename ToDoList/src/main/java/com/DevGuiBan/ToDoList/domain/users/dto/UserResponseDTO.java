package com.DevGuiBan.ToDoList.domain.users.dto;

import com.DevGuiBan.ToDoList.domain.users.User;
import com.DevGuiBan.ToDoList.domain.users.UserRole;

import java.util.UUID;

public record UserResponseDTO (UUID id, String name, String email, UserRole role) {
    public UserResponseDTO (User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
