package com.example.demo.user.exception;

import com.example.demo.common.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
