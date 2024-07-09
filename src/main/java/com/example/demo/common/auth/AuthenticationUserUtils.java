package com.example.demo.common.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 로그인 이후 모든 요청에서 getCurrentUserId 로 header의 token 값을 추출하여
 * 현재 사용자 찾아냄
 */
@Component
@RequiredArgsConstructor
public class AuthenticationUserUtils {

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
