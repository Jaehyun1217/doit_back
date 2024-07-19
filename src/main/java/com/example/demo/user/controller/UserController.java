package com.example.demo.user.controller;


import com.example.demo.common.response.SuccessResponse;
import com.example.demo.user.dto.request.SignInRequestDto;
import com.example.demo.user.dto.request.SignUpRequestDto;
import com.example.demo.user.dto.response.*;
import com.example.demo.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public SuccessResponse<SignUpResponseDto> signup(@Valid @RequestBody SignUpRequestDto dto){
        SignUpResponseDto response = userService.signup(dto);
        return SuccessResponse.of(response);
    }

    /**
     * 로그인
     */
    @PostMapping("/signin")
    public SuccessResponse<SignInResponseDto> signIn(@RequestBody SignInRequestDto dto) {
        SignInResponseDto response = userService.signIn(dto);
        return SuccessResponse.of(response);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }

    /**
     * 아이디 찾기
     */
    @GetMapping("/findId")
    public SuccessResponse<FindIdResponseDto> findId(@RequestParam("email") String email) {
        return SuccessResponse.of(userService.findId(email));
    }

    /**
     * 비밀번호 찾기
     */
    @GetMapping("/findPss")
    public SuccessResponse<FindPssResponseDto> findPss(@RequestParam("userId") String userId, @RequestParam("email") String email) {
        return SuccessResponse.of(userService.findPss(userId, email));
    }

    /**
     * 마이페이지
     */
    @GetMapping("/mypage")
    public ResponseEntity<UserInfoResponseDto> getMyPage(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userService.mypage(user));
    }

    /**
     * 마이페이지 계정 삭제
     */
    @DeleteMapping("/mypage/account")
    public ResponseEntity<UserInfoResponseDto> deleteAccount(@RequestParam("userId") String userId, @RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("email")String email) {
        userService.deleteAccount(userId, username, password, email);
        return  ResponseEntity.noContent().build();

    }

}
