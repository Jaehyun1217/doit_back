package com.example.demo.user.dto.response;

import lombok.Builder;

public record UserInfoResponseDto(
        String UserId,
        String email,
        String username
) {
    @Builder
    public UserInfoResponseDto(String UserId, String email, String username) {
        this.UserId = UserId;
        this.email = email;
        this.username = username;
    }
}
