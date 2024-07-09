package com.example.demo.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Getter;


@Getter
public class SignUpRequestDto {

    /**
     * @NotNull
     * - @RequestBody 에 필드가 null 로 들어오면 안된다는 제약 조건
     * - ex) { "userId" : null }
     *
     * @NotEmpty
     * - @RequestBody 에 필드가 "" 면 안된다는 제약 조건
     * - ex) { "userId" : "" }
     *- 제약 조건에 걸린 경우 응답
     * {
     *     "code": "GLOBAL_400_2",
     *     "message": "비어있을 수 없습니다.",
     *     "httpStatus": 400
     * }
     *
     * @NotBlank
     * - @RequestBody 에 필드가 공백으로만 이루어져 있으면 안된다는 제약 조건
     * - ex) { "userId" : " " }
     *- 제약 조건에 걸린 경우 응답
     * {
     *     "code": "GLOBAL_400_2",
     *     "message": "공백으로만 이루어질 수 없습니다.",
     *     "httpStatus": 400
     * }
     *
     * @Email
     * - 이메일 형식 맞아야 함
     * - ex) { "email" : "example@naver.com" }
     * - 제약 조건에 걸린 경우 응답
     * {
     *     "code": "GLOBAL_400_2",
     *     "message": "유효한 이메일 형식이 아닙니다.",
     *     "httpStatus": 400
     * }
     *
     * 비밀번호 필드에 영소문자, 영대문자, 특수기호 포함 여부 등을 설정하고 싶다면
     * @Pattern 을 사용하면 됨
     */

    @NotNull(message = "필수값입니다.")
    @NotEmpty(message = "비어있을 수 없습니다.")
    @NotBlank(message = "공백으로만 이루어질 수 없습니다.")
    private String userId;

    @NotNull(message = "필수값입니다.")
    @NotEmpty(message = "비어있을 수 없습니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @NotBlank(message = "공백으로만 이루어질 수 없습니다.")
    private String email;

    @NotNull(message = "필수값입니다.")
    @NotEmpty(message = "비어있을 수 없습니다.")
    @NotBlank(message = "공백으로만 이루어질 수 없습니다.")
    private String username;

    @NotNull(message = "필수값입니다.")
    @NotEmpty(message = "비어있을 수 없습니다.")
    @NotBlank(message = "공백으로만 이루어질 수 없습니다.")
    private String password;
}
