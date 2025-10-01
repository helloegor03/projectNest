package com.helloegor03.project.config;

import config.JwtUtil;
import org.springframework.beans.factory.annotation.Value;

public class CustomJwtUtil extends JwtUtil {
    public CustomJwtUtil(@Value("${token.signing.key}") String secret,
                         @Value("${token.signing.lifetime}") long lifetime) {
        super(secret, lifetime);
    }
}
