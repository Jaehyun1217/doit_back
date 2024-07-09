package com.example.demo.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 메일과 관련된 설정들이 존재하는 클래스
 * 필드들은 application-email.yml 에 작성되어 있는 값들을 할당
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String mailSmtpStarttlsRequired;

    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private String mailSmtpConnectionTimeout;

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String mailSmtpTimeout;

    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private String mailSmtpWriteTimeout;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(mailHost);
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);
        javaMailSender.setPort(mailPort);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", mailSmtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        properties.setProperty("mail.smtp.starttls.required", mailSmtpStarttlsRequired);
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.trust", mailHost);
        properties.setProperty("mail.smtp.connectiontimeout", mailSmtpConnectionTimeout);
        properties.setProperty("mail.smtp.timeout", mailSmtpTimeout);
        properties.setProperty("mail.smtp.writetimeout", mailSmtpWriteTimeout);
        return properties;
    }
}