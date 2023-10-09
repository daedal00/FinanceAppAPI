package com.daedal00.app.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.daedal00.app.api.dto.UserDTO;
import com.daedal00.app.model.User;
import com.daedal00.app.repository.UserRepository;
import com.daedal00.app.service.UserService;
import com.daedal00.app.util.JwtUtil;

import lombok.Data;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final User userFromDb = userService.findByUsername(authenticationRequest.getUsername());

        if (userFromDb == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
        }

        // Check if the password in the database matches the provided password
        if (!authenticationRequest.getPassword().equals(userFromDb.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password.");
        }

        // If password matches, generate JWT token
        final String token = jwtUtil.generateToken(userFromDb.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        User existingUserByUsername = userRepository.findByUsername(userDTO.getUsername());
        User existingUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (existingUserByUsername != null || existingUserByEmail != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or Email already exists");
        }

        // Save the plain password directly
        userDTO.setPassword(userDTO.getPassword());

        UserDTO savedUserDTO = userService.saveUser(userDTO);
        return ResponseEntity.ok(savedUserDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.updateUser(id, userDTO);
        if (updatedUserDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUserDTO);
    }
    
    public static class AuthenticationResponse {
        private final String jwt;

        public AuthenticationResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }

    @Data
    public static class JwtRequest {
        private String username;
        private String password;
    }
}
