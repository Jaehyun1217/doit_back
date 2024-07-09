package com.example.demo.email.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 이메일 인증을 위한 dto 클래스
 */
@Getter
@Setter
public class VerificationEmailRequestDto {
    private String email;
    private String code;
}
