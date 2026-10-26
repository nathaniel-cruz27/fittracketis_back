package com.nathyarik.fittracketis.controller;

import com.nathyarik.fittracketis.dto.UserPreferencesUpdateDTO;
import com.nathyarik.fittracketis.dto.UserRegistrationDTO;
import com.nathyarik.fittracketis.dto.UserProfileUpdateDTO;
import com.nathyarik.fittracketis.entity.User;
import com.nathyarik.fittracketis.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDTO dto) {
        try {
            User newUser = userService.registerUser(dto);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED); //201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); //400, email already exists
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable UUID id, @RequestBody UserProfileUpdateDTO dto) {
        try {
            User updatedUser = userService.updateProfile(id, dto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/preferences")
    public ResponseEntity<?> updatePreferences(@PathVariable UUID id, @RequestBody UserPreferencesUpdateDTO dto) {
        try {
            User updatedUser = userService.updatePreferences(id, dto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}