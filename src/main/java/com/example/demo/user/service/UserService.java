package com.example.demo.user.service;

import com.example.demo.user.dto.response.*;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.exception.EmailAlreadyExistException;
import com.example.demo.user.exception.IdAlreadyExistException;
import com.example.demo.user.exception.PasswordIncorrectException;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.dto.request.SignInRequestDto;
import com.example.demo.user.dto.request.SignUpRequestDto;
import com.example.demo.email.service.EmailService;
import com.example.demo.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final Map<String, SignUpRequestDto> temporaryUserStorage = new HashMap<>(); // 임시 저장소


    /**
     * 회원가입 로직
     * email, userId 중복 확인 후 중복인 경우 에러 반환
     * 그렇지 않은 경우 인증번호 확인 메일 전송
     */
    public SignUpResponseDto signup(SignUpRequestDto dto) {

        // email 중복 확인
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException();
        }

        // userId 중복 확인
        if (userRepository.findByUsername(dto.getUserId()).isPresent()) {
            throw new IdAlreadyExistException();
        }

        // 이메일 인증 요청
        emailService.sendVerificationMail(dto);
        temporaryUserStorage.put(dto.getEmail(), dto);

        // 아직 user 가 만들어진 상태는 아니므로 id는 null 반환
        return SignUpResponseDto.builder().id(null).build();
    }

    /**
     * 회원가입 완료 시 호출되는 메소드
     * DB 에 user 저장
     */
    public void completeSignUp(String email) {
        SignUpRequestDto dto = temporaryUserStorage.get(email);
        if (dto != null) {
            Long createdId = userRepository.save(User.from(dto)).getId();
            temporaryUserStorage.remove(email);
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * 로그인 로직
     */
    public SignInResponseDto signIn(SignInRequestDto dto) {

        // 유저를 찾고 없으면 에러 반환
        User user = userRepository.findByUsername(dto.getUserId()).orElseThrow(UserNotFoundException::new);

        // 유저의 비밀번호가 일치하는지 확인하고 일치하지 않으면 에러 반환
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new PasswordIncorrectException();
        }

        String token = jwtTokenProvider.createToken(user.getUsername(), UserType.USER);
        return SignInResponseDto.builder().accessToken(token).build();
    }

    /**
     * 아이디 찾기 로직
     */
    public FindIdResponseDto findId(String email) {

        if (userRepository.findByEmail(email).isEmpty()) {
            throw new UserNotFoundException();
        }

        String userId = userRepository.findByEmail(email).get().getUsername();
        return FindIdResponseDto.builder().userId(userId).build();
    }

    /**
     * 비밀번호 찾기 로직
     */
    public FindPssResponseDto findPss(String userId, String email) {

        if (userRepository.findByUsernameAndEmail(userId, email).isEmpty()) {
            throw new UserNotFoundException();
        }

        String password = userRepository.findByUsernameAndEmail(userId, email).get().getPassword();
        return FindPssResponseDto.builder().password(password).build();
    }

    public UserInfoResponseDto mypage(org.springframework.security.core.userdetails.User user){
        System.out.println(user);
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            User userinfo = userRepository.findByUsername(user.getUsername()).get();
            return UserInfoResponseDto.builder()
                    .UserId(userinfo.getUsername())
                    .email(userinfo.getEmail())
                    .username(userinfo.getName())
                    .build();
        }else {
            throw new RuntimeException("회원 정보 없음");
        }
    }
    /**
     * 마이페이지 정보 수정
     *//*
    public UserInfoResponseDto updateUser(UserDetails user, UpdateUserRequestDto updateUserRequestDto) {
        User userinfo = userRepository.findByUserName(user.getUsername())
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));

        // 이메일 변경을 허용하는 경우 이메일 중복 확인
        if (!userinfo.getEmail().equals(updateUserRequestDto.getEmail()) &&
                userRepository.findByEmail(updateUserRequestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException();
        }

        userinfo.setEmail(updateUserRequestDto.getEmail());
        userinfo.setName(updateUserRequestDto.getUsername());
        userRepository.save(userinfo);

        return UserInfoResponseDto.builder()
                .UserId(userinfo.getUsername())
                .email(userinfo.getEmail())
                .username(userinfo.getName())
                .build();
    }*/

    /**
     * 마이페이지 계정 삭제
     */
    public void deleteUser(UserDetails user) {
        User userinfo = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));

        userRepository.delete(userinfo);
    }

    public void deleteAccount() {
    }

    public void deleteAccount(String userId, String username, String password, String email) {
        User user = userRepository.findByUsernameAndEmail(userId, email)
                .orElseThrow(UserNotFoundException::new);

        if (!user.getPassword().equals(password) || !user.getName().equals(username)){
            throw new PasswordIncorrectException();
        }
        userRepository.delete(user);
    }
    public User getUser(org.springframework.security.core.userdetails.User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            return userRepository.findByUsername(user.getUsername()).get();
        }else{
            throw new UserNotFoundException();
        }
    }
}
