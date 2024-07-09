package com.example.demo.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindPssResponseDto {
    private String password;
}
