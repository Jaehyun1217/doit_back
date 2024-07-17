package com.example.demo.user.exception;

import com.example.demo.common.exception.BaseException;

public class EmailAlreadyExistException extends BaseException {
    public EmailAlreadyExistException() {
        super(UserErrorCode.EMAIL_ALREADY_EXIST);
    }
}
