package com.example.demo.common.security;

import com.example.demo.user.exception.UserErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;
import com.example.demo.common.exception.ErrorResponse;

/**
 * JwtTokenProvider 클래스에 존재하는 메소드를 사용하여 filter 처리
 */
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * logger 는 확인 용으로 넣어놓은 것이라서
     * 추후에 삭제해도 로직에는 영향을 주지 않음
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    /**
     * 사용자 인증을 처리
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            logger.debug("Token detected: {}", token);
            if (jwtTokenProvider.validateToken(token)) {
                logger.debug("Token is valid");
                jwtTokenProvider.setSecurityContext(token);
            } else {
                unauthorizedResponse(servletResponse, "존재하지 않는 토큰입니다.");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 토큰이 유효하지 않거나 존재하지 않는 경우
     * 응답을 customizing 하기 위한 메소드
     * 해당 메소드가 없으면 HTML 500 이 반환됨
     */
    private void unauthorizedResponse(ServletResponse servletResponse, String message) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ErrorResponse errorResponse = ErrorResponse.of(UserErrorCode.UNAUTHORIZED_USER, message);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(UserErrorCode.UNAUTHORIZED_USER.getHttpStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
