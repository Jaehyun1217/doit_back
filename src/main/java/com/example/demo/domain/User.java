package com.example.demo.domain;

import com.example.demo.dto.SignupRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity //table선언
@NoArgsConstructor //오류 발생 방지 NULL
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String userId;
    private String password;
    private String email;
    private String username;
    @Enumerated(EnumType.STRING)
    private UserType userType;


    //회원가입시 필요한 메소드
    public static User from(SignupRequest request){

        return User.builder()
                .userId(request.userID())
                .password(request.password())
                .email(request.email())
                .username(request.username())
                .userType(UserType.USER)
                .build();
    }
    @Builder
    public User(String userId, String password, String email, String username, UserType userType) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.username = username;
        this.userType = userType;
    }
}
