package com.nathyarik.fittracketis.controller;

import com.nathyarik.fittracketis.config.JwtUtil;
import com.nathyarik.fittracketis.dto.AuthResponseDTO;
import com.nathyarik.fittracketis.dto.LoginRequestDTO;
import com.nathyarik.fittracketis.entity.User;
import com.nathyarik.fittracketis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(new AuthResponseDTO(token));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong credentials"); // 401
    }
}