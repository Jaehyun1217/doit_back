package com.example.demo.service;


import com.example.demo.domain.Repostory.UserRepostory;
import com.example.demo.domain.User;
import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.SignupRespnse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignService {
    private final UserRepostory userRepostory;

    public SignupRespnse signup(SignupRequest dto){
        User user = userRepostory.save(User.from(dto));
        try{
            userRepostory.flush();
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다.");
        }
        return SignupRespnse.from(user);
    }
}
