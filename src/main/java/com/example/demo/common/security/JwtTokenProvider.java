package com.example.demo.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

/**
 * Jwt 와 관련된 기능들을 모아놓은 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long validityInMilliseconds;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * token 값 생성
     */
    public String createToken(String userSpecification) {
        Claims claims = Jwts.claims();
        claims.put("username",userSpecification);
        return Jwts.builder()
                // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setHeaderParam("typ","JWT")
                // JWT 토큰 제목
                .setSubject(userSpecification)
                // JWT 토큰 발급 시간
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                // JWT 토큰 만료 시간
                .setExpiration(Date.from(Instant.now().plus(validityInMilliseconds, ChronoUnit.HOURS)))
                .compact(); // JWT 토큰 생성
    }
    public String validateTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
