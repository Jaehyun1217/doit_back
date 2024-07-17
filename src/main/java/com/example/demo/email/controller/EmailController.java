package com.example.demo.email.controller;

import com.example.demo.common.response.SuccessResponse;
import com.example.demo.email.service.EmailService;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    @GetMapping("/verify")
    public SuccessResponse<Object> verify(@RequestParam String email, @RequestParam String code) {
        boolean isVerified = emailService.verifyMail(email, code);
        if (isVerified) {
            userService.completeSignUp(email);
            return SuccessResponse.of("이메일 인증 및 회원가입 성공");
        } else {
            return SuccessResponse.of("이메일 인증 실패");
        }
    }
}
