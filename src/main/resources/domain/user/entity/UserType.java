package com.example.demo.domain.user.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
