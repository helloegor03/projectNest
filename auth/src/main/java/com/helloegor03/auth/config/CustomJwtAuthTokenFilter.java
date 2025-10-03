package com.helloegor03.auth.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import config.JwtUtil;
import config.JwtAuthTokenFilter;


public class CustomJwtAuthTokenFilter extends JwtAuthTokenFilter {

    public CustomJwtAuthTokenFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        super(userDetailsService, jwtUtil);
    }
}
