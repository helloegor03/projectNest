package com.helloegor03.project.config;

import com.helloegor03.project.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service", url = "${APPLICATION_CONFIG_USERS_URL}")
public interface EmployeeClient {
    @GetMapping("/auth/find/{id}")
    UserResponse findUserById(@PathVariable("id") Long id);
}

