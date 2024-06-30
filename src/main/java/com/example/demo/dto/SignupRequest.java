package com.example.demo.dto;

public record SignupRequest(
        String userID,
        String password,
        String email,
        String username
    ) {
}
