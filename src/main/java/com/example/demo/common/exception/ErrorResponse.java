package com.example.demo.common.exception;

import com.example.demo.common.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * BaseResponse 를 상속하여 에러 시 응답을 처리하는 클래스
 */
@Getter
@ToString
public class ErrorResponse extends BaseResponse {

    private final int httpStatus;

    @Builder
    public ErrorResponse(String code, String message, int httpStatus) {
        super(code, message);
        this.httpStatus = httpStatus;

    }

    public static ErrorResponse of(BaseErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .httpStatus(errorCode.getHttpStatus())
                .build();
    }

    public static ErrorResponse of(BaseErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(message)
                .httpStatus(errorCode.getHttpStatus())
                .build();
    }

    public static ErrorResponse of(String code, String message, int httpStatus) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }
}
