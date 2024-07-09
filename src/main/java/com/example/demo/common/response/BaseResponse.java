package com.example.demo.common.response;

import com.example.demo.common.exception.BaseErrorCode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 기본 응답 클래스
 * 모든 응답의 ResponseBody 에는 code 필드와 message 필드가 포함됨
 */
@Getter
@ToString
@RequiredArgsConstructor
public class BaseResponse {

    private final String code;
    private final String message;

    public static BaseResponse of(BaseErrorCode errorCode) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage());
    }

    public static BaseResponse of(BaseErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), message);
    }

    public static BaseResponse of(String code, String message) {
        return new BaseResponse(code, message);
    }
}
