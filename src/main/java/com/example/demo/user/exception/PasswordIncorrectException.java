package com.example.demo.user.exception;

import com.example.demo.common.exception.BaseException;

public class PasswordIncorrectException extends BaseException {
    public PasswordIncorrectException() {
        super(UserErrorCode.PASSWORD_INCORRECT);
    }
}
