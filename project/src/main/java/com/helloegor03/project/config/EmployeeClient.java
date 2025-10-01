package com.helloegor03.project.config;

import com.helloegor03.project.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users", url = "${application.config.users-url}")
public interface EmployeeClient {
    @GetMapping("/auth/find/{id}")
    UserResponse findUserById(@PathVariable("id") Long id);
}

