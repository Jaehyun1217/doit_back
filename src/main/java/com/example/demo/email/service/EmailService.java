package com.example.demo.email.service;

import com.example.demo.email.dto.EmailMessageDto;
import com.example.demo.user.dto.request.SignUpRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 이메일과 관련된 비즈니스 로직이 존재하는 클래스
 */
@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${server.url}")
    private String serverUrl;

    private final JavaMailSender javaMailSender;
    private final Map<String, String> verificationCodes = new HashMap<>(); // 임시 저장을 위함

    /**
     * 이메일을 보내는 메소드
     */
    public void sendVerificationMail(SignUpRequestDto dto) {
        String verificationCode = generateVerificationCode();
        verificationCodes.put(dto.getEmail(), verificationCode);

        // 보낼 이메일 내용 구성
        String verificationLink = serverUrl+"/api/emails/verify?email=" + dto.getEmail() + "&code=" + verificationCode;
        EmailMessageDto emailMessageDto = EmailMessageDto.builder()
                .to(dto.getEmail())
                .subject("이메일 인증 링크")
                .content("<a href=\"" + verificationLink + "\">이메일 인증하기</a>")
                .build();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailMessageDto.getTo());
            mimeMessageHelper.setSubject(emailMessageDto.getSubject());
            mimeMessageHelper.setText(emailMessageDto.getContent(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("email error : " + e.getMessage());
        }
    }

    public boolean verifyMail(String email, String code) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(email);
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        // 인증 코드를 생성하는 로직 -> 보안적인 부분이라 개발자분이 만드는 것이 좋아보임
        return "123456"; // 예시로 123456 반환, 실제로는 랜덤한 코드 생성 필요
    }

}
