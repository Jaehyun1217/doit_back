package com.example.demo.domain.user.exception;

import com.example.demo.common.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(com.example.demo.domain.user.exception.UserErrorCode.USER_NOT_FOUND);
    }
}
