package com.example.demo.user.service;


import com.example.demo.common.security.JwtTokenProvider;
import com.example.demo.domain.user.entity.UserType;
import com.example.demo.domain.user.exception.EmailAlreadyExistException;
import com.example.demo.domain.user.exception.IdAlreadyExistException;
import com.example.demo.domain.user.exception.PasswordIncorrectException;
import com.example.demo.domain.user.exception.UserNotFoundException;
import com.example.demo.email.service.EmailService;
import com.example.demo.user.dto.response.SignUpResponseDto;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.user.dto.request.SignInRequestDto;
import com.example.demo.user.dto.request.SignUpRequestDto;
import com.example.demo.user.dto.response.FindIdResponseDto;
import com.example.demo.user.dto.response.FindPssResponseDto;
import com.example.demo.user.dto.response.SignInResponseDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        if (userRepository.findByUserId(dto.getUserId()).isPresent()) {
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
        User user = userRepository.findByUserId(dto.getUserId()).orElseThrow(UserNotFoundException::new);

        // 유저의 비밀번호가 일치하는지 확인하고 일치하지 않으면 에러 반환
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new PasswordIncorrectException();
        }

        String token = jwtTokenProvider.createToken(user.getUserId(), UserType.USER);
        return SignInResponseDto.builder().accessToken(token).build();
    }

    /**
     * 아이디 찾기 로직
     */
    public FindIdResponseDto findId(String email) {

        if (userRepository.findByEmail(email).isEmpty()) {
            throw new UserNotFoundException();
        }

        String userId = userRepository.findByEmail(email).get().getUserId();
        return FindIdResponseDto.builder().userId(userId).build();
    }

    /**
     * 비밀번호 찾기 로직
     */
    public FindPssResponseDto findPss(String userId, String email) {

        if (userRepository.findByUserIdAndEmail(userId, email).isEmpty()) {
            throw new UserNotFoundException();
        }

        String password = userRepository.findByUserIdAndEmail(userId, email).get().getPassword();
        return FindPssResponseDto.builder().password(password).build();
    }
}
