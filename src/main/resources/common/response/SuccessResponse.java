package com.example.demo.common.response;

import lombok.Getter;
import lombok.ToString;

/**
 * API 호출 성공 시 ResponseBody 에는 BaseResponse 를 상속받았기 때문에
 * BaseResponse 클래스에 존재하는 code, message 필드와
 * 이 클래스(SuccessResponse<T>)에 존재하는 data 필드가 포함됨
 *
 * 예시 ResponseBody)
 * {
 *     "code": "200",
 *     "message": "호출에 성공하였습니다.",
 *     "data": {
 *         "id": 1
 *     }
 * }
 *
 */
@Getter
@ToString
public class SuccessResponse<T> extends BaseResponse {

    private final T data;

    public SuccessResponse(T data) {
        super("200", "호출에 성공하였습니다.");
        this.data = data;
    }

    public SuccessResponse(T data, String code) {
        super(code, "호출에 성공하였습니다.");
        this.data = data;
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<>(data);
    }

    public static <T> SuccessResponse<T> of(T data, String code) {
        return new SuccessResponse<>(data, code);
    }

    public static <T> SuccessResponse<T> empty() {
        return new SuccessResponse<>(null);
    }
}
