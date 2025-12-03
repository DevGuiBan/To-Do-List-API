package com.DevGuiBan.ToDoList.controllers;

import com.DevGuiBan.ToDoList.domain.users.User;
import com.DevGuiBan.ToDoList.domain.users.dto.UserResponseDTO;
import com.DevGuiBan.ToDoList.infra.SecurityConfigurations;
import com.DevGuiBan.ToDoList.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();

            return ResponseEntity.ok(userList);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

























