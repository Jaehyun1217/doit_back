package com.example.demo.common.response;

import com.example.demo.common.exception.BaseErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 에러 발생 시 ResponseBody 에는 BaseResponse 를 상속받았기 때문에
 * BaseResponse 클래스에 존재하는 code, message 필드와
 * 이 클래스(ErrorResponse)에 존재하는 httpStatus 필드가 포함됨
 *
 * 예시 ResponseBody)
 * {
 *     "code": "BAD_REQUEST_ERROR",
 *     "message": "잘못된 요청입니다.",
 *     "httpStatus": 404
 * }
 *
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
