package com.example.demo.common.security;

import com.example.demo.common.config.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 보안과 관련된 클래스
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private String[] allowUrl = {"/","/api/users/signup", "/api/users/signin", "/api/users/findId", "/api/users/findPss", "/api/emails/verify"};
    /**
     * 암호화 메소드
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * csrf 비활성화: cache 가 아닌 token 을 사용하므로 csrf 비활성화
     * session 정책 STATELESS: token 을 사용하면 서버는 STATELESS, 즉 어떤 요청에 대해서도 같은 응답을 반환
     * authorizeHttpRequests 부분
     * .requestMatchers("/api경로1", "api경로2).permitAll(): USER, ADMIN, 권한이 부여되어 있지 않은 모두가 사용 가능한 URI
     * .requestMatchers("/api경로1", "api경로2).hasAnyAuthority("USER","ADMIN"): USER 혹은 ADMIN 권한이 부여된 user 만 사용 가능한 URI
     * .anyRequest().hasAnyAuthority("ADMIN"): 이외 모든 기능은 ADMIN 권한이 부여된 user 만 사용 가능
     * .addFilterBefore: 사용자 인증을 위해 작성한 custom filter 클래스를 등록
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(allowUrl).permitAll()
//                        .requestMatchers("/api/users/logout", "/api/test").hasAnyAuthority("USER","ADMIN")
                                .anyRequest().authenticated()
//                        .anyRequest().hasAnyAuthority("ADMIN")
                )
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
