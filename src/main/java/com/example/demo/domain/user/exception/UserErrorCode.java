package com.example.demo.domain.user.exception;

import com.example.demo.common.exception.BaseErrorCode;
import com.example.demo.common.constant.StaticValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User 엔티티와 관련된 에러 코드를 모아놓음
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    EMAIL_ALREADY_EXIST(StaticValue.BAD_REQUEST, "USER_400_1", "이미 사용중인 이메일 입니다."),
    ID_ALREADY_EXIST(StaticValue.BAD_REQUEST, "USER_400_2", "이미 사용중인 아이디 입니다."),
    PASSWORD_INCORRECT(StaticValue.UNAUTHORIZED, "USER_401_1", "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED_USER(StaticValue.FORBIDDEN, "USER_403_1", "인증되지 않은 사용자입니다."),
    USER_NOT_FOUND(StaticValue.NOT_FOUND, "USER_404_1", "존재하지 않는 아이디 입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
