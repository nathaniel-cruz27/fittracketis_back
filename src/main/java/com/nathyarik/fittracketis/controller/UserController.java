package com.nathyarik.fittracketis.controller;

import com.nathyarik.fittracketis.dto.UserRegistrationDTO;
import com.nathyarik.fittracketis.entity.User;
import com.nathyarik.fittracketis.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}