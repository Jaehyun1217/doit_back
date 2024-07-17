package com.example.demo.user.dto.request;

import lombok.Getter;
//아이디 입력
@Getter
public class SignInRequestDto {
    private String userId;
    private String password;
}
