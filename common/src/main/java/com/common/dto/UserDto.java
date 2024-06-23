package com.common.dto;

import com.common.domain.user.Role;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String userName,
        Role userRole,
        String userNickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}