package com.helloegor03.auth.service;

import com.helloegor03.auth.config.JwtUtil;
import com.helloegor03.auth.dto.AuthRequest;
import com.helloegor03.auth.dto.RegisterRequest;
import com.helloegor03.auth.model.User;
import com.helloegor03.auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       UserRepository userRepository)
    {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterRequest input){
        User user = new User(input.getEmail(), input.getUsername(), passwordEncoder.encode(input.getPassword()));
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("User is already exists");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email is aready exists");
        }
        return userRepository.save(user);
    }

    public Authentication authenticateUser(AuthRequest input) {
        User user = userRepository.findByUsername(input.getUsername()).orElseThrow(()
                -> new UsernameNotFoundException("Not found this user"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()
                )
        );
        return authentication;
    }

    public void deleteUser(Long id){
        if(userRepository.findById(id).isEmpty()){
            throw new RuntimeException("Cannot found this user");
        }
        userRepository.deleteById(id);
    }



}

