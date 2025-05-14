package com.dat.backend.blogservice.service;

import com.dat.backend.blogservice.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserService {
    @GetMapping("/api/v1/user/{id}")
    UserResponseDto getUserById(@PathVariable Long id);
}
