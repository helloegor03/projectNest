package com.helloegor03.auth.service;

import com.helloegor03.auth.dto.AuthRequest;
import com.helloegor03.auth.dto.RegisterRequest;
import com.helloegor03.auth.model.User;
import com.helloegor03.auth.repository.UserRepository;
import com.helloegor03.common.exceptions.auth.UserIsAlreadyExistsException;
import com.helloegor03.common.exceptions.auth.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            throw new UserIsAlreadyExistsException("User is already exists");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserIsAlreadyExistsException("User with this email is aready exists");
        }
        return userRepository.save(user);
    }

    public Authentication authenticateUser(AuthRequest input) {
        User user = userRepository.findByUsername(input.getUsername()).orElseThrow(()
                -> new UsernameNotFoundException("User with username not found "+ input.getUsername()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()
                )
        );
        return authentication;
    }

    public void deleteUser(Long id){
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User not found with id: "+ id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found with id: "+ id);
        }
        return userRepository.findById(id);
    }



}

