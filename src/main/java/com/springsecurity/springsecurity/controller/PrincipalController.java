package com.springsecurity.springsecurity.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.springsecurity.controller.request.CreateUserDTO;
import com.springsecurity.springsecurity.models.ERole;
import com.springsecurity.springsecurity.models.RoleEntity;
import com.springsecurity.springsecurity.models.UserEntity;
import com.springsecurity.springsecurity.repositories.UserRepository;

import jakarta.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello() {
        return "Hello not secured ✖";
    }

    @GetMapping("/hellosecured")
    public String helloSecured() {
        return "Hello secured ✔";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }
    
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") String id) {
        userRepository.deleteById(Long.parseLong(id));
        return "User deleted id: " + id;
    }
}
