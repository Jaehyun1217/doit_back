package com.example.demo.common.security;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.exception.UserNotFoundException;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * SpringSecurity 와 개발자가 작성한 User 테이블을 매핑시키기 위한 클래스
 */
@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserId(username).orElseThrow(UserNotFoundException::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(String.valueOf(user.getUserType()))
                .build();
    }
}
