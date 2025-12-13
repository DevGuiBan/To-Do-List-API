package com.DevGuiBan.ToDoList.controllers;

import com.DevGuiBan.ToDoList.domain.users.dto.UserResponseDTO;
import com.DevGuiBan.ToDoList.infra.SecurityConfigurations;
import com.DevGuiBan.ToDoList.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@Tag(name = "Gerenciamento de Usuário", description = "Controlador de usuário com seus métodos para gerenciamento dos usuários. ")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @Operation(summary = "Todos os usuários ADMIN", description = "Get para todos os usuários, seguindo o UserResponseDTO.")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();

            if(userList.isEmpty()) return ResponseEntity.status(204).body("Nenhum usuário foi encontrado.");

            return ResponseEntity.ok(userList);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("userBy/{id}")
    @Operation(summary = "Usuário por ID ADMIN", description = "Get para usuário buscando pelo ID, seguindo o UserResponseDTO.")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        try {
            var user = userRepository.findById(id);

            if(user.isEmpty()) return ResponseEntity.status(204).body("Usuário não Encontrado.");

            return ResponseEntity.ok(new UserResponseDTO(user.get()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    @Operation(summary = "Delete usuário por ID ADMIN", description = "delete usuário por ID.")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
                return userRepository.findById(id).map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok("Usuário Deletado com Sucesso!");
                }).orElseGet(() -> ResponseEntity.status(204).body("Usuário não encontrado"));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

























