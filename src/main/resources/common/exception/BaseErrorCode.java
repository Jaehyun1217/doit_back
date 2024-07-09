package com.example.demo.common.exception;

/**
 * 기본 에러 코드에 사용되는 인터페이스
 */
public interface BaseErrorCode {
    String getCode();
    String getMessage();
    int getHttpStatus();
}
