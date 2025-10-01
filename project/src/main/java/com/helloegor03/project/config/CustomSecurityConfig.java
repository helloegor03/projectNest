package com.helloegor03.project.config;

import com.helloegor03.project.service.UserDetailsServiceImpl;
import config.SecurityConfig;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomSecurityConfig extends SecurityConfig {
    public CustomSecurityConfig(UserDetailsServiceImpl userDetailsService,
                                AuthenticationEntryPoint unauthorizedHandler) {
        super(userDetailsService, unauthorizedHandler);
    }
}
