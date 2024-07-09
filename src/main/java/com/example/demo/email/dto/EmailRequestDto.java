package com.example.demo.email.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 이메일 수신자를 넘겨주는 dto 클래스
 * - email: 수신자
 */
@Getter
@Setter
public class EmailRequestDto {
    private String email;
}
