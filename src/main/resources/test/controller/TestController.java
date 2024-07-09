package com.example.demo.test.controller;

import com.example.demo.common.auth.AuthenticationUserUtils;
import com.example.demo.common.response.SuccessResponse;
import com.example.demo.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final AuthenticationUserUtils authenticationUserUtils;

    /**
     * 게시글 작성이라고 가정
     * - 성공 시 응답
     * {
     *     "code": "200",
     *     "message": "호출에 성공하였습니다.",
     *     "data": null
     * }
     *
     * - 실패 시 응답
     *{
     *     "code": "USER_403_1",
     *     "message": "존재하지 않는 토큰입니다.",
     *     "httpStatus": 403
     * }
     *
     * String currentUserId = authenticationUserUtils.getCurrentUserId();
     * - header 에서 token 추출하여 검증하는 코드
     */
    @PostMapping
    public SuccessResponse<Object> test() {
        String currentUserId = authenticationUserUtils.getCurrentUserId();
        testService.test(currentUserId); // 실제 비즈니스 로직 작성 필요
        return SuccessResponse.empty();
    }

}
