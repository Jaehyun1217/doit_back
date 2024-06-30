package com.example.demo.dto;

import com.example.demo.domain.User;

import java.util.UUID;

public record SignupRespnse(
        UUID id,
        String username,
        String email
) {
    public static SignupRespnse from(User user){
        return new SignupRespnse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
