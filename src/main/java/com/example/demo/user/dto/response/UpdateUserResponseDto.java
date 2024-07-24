package com.example.demo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserResponseDto {
    private String userId;
    private String email;
    private String username;
}
