package com.example.demo.user.exception;

import com.example.demo.common.exception.BaseException;

public class IdAlreadyExistException extends BaseException {
    public IdAlreadyExistException() {
        super(UserErrorCode.ID_ALREADY_EXIST);
    }
}
