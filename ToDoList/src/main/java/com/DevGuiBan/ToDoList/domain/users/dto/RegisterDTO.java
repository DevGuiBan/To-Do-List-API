package com.DevGuiBan.ToDoList.domain.users.dto;

import com.DevGuiBan.ToDoList.domain.users.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
