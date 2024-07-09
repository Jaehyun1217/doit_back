package com.example.demo.domain.user.exception;

import com.example.demo.common.exception.BaseException;

public class UnauthorizedUserException extends BaseException {
    public UnauthorizedUserException() {
        super(UserErrorCode.UNAUTHORIZED_USER);
    }
}