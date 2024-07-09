package com.example.demo.domain.user.exception;

import com.example.demo.common.exception.BaseException;

public class EmailAlreadyExistException extends BaseException {
    public EmailAlreadyExistException() {
        super(UserErrorCode.EMAIL_ALREADY_EXIST);
    }
}
