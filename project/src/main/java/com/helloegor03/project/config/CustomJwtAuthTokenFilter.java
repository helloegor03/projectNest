package com.helloegor03.project.config;

import config.JwtAuthTokenFilter;
import config.JwtUtil;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomJwtAuthTokenFilter extends JwtAuthTokenFilter {
    public CustomJwtAuthTokenFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        super(userDetailsService, jwtUtil);
    }
}
