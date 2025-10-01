package com.helloegor03.auth.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import config.JwtUtil;
import config.JwtAuthTokenFilter;

@Component
public class CustomJwtAuthTokenFilter extends JwtAuthTokenFilter {

    public CustomJwtAuthTokenFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        super(userDetailsService, jwtUtil);
    }
}
