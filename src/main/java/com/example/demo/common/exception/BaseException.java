package com.example.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RuntimeException 을 상속받는 클래스
 */
@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private final BaseErrorCode errorCode;
}