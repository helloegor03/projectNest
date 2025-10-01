package com.helloegor03.auth.controller;

import com.helloegor03.auth.config.JwtUtil;
import com.helloegor03.auth.dto.AuthRequest;
import com.helloegor03.auth.dto.JwtResponse;
import com.helloegor03.auth.dto.RegisterRequest;
import com.helloegor03.auth.dto.UserResponse;
import com.helloegor03.auth.model.User;
import com.helloegor03.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest input){
        try{
            User user = authService.registerUser(input);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody AuthRequest input){
        Authentication authentication = authService.authenticateUser(input);
        String token = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
        User user = authService.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Маппим в DTO (можно руками, можно через MapStruct)
        UserResponse response = new UserResponse(
                user.getId(),
                user.getUsername()

        );

        return ResponseEntity.ok(response);
    }

}
