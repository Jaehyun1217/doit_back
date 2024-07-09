package com.example.demo.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 이메일 전송 시 사용되는 dto 클래스
 * - to: 수신자
 * - subject: 메일 제목
 * - content: 메일 내용
 */
@Getter
@Setter
@Builder
public class EmailMessageDto {
    private String to;
    private String subject;
    private String content;
}
