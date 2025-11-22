package com.DevGuiBan.ToDoList.controllers;

import com.DevGuiBan.ToDoList.domain.users.User;
import com.DevGuiBan.ToDoList.domain.users.dto.AuthenticationDTO;
import com.DevGuiBan.ToDoList.domain.users.dto.LoginResponseDTO;
import com.DevGuiBan.ToDoList.domain.users.dto.RegisterDTO;
import com.DevGuiBan.ToDoList.infra.SecurityConfigurations;
import com.DevGuiBan.ToDoList.infra.TokenService;
import com.DevGuiBan.ToDoList.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassoword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassoword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword ,data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
