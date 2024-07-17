package com.example.demo.user.entity;

import com.example.demo.common.entity.BaseTimeEntity;
import com.example.demo.user.dto.request.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User extends BaseTimeEntity {
    /**
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * - PK 생성을 데이터베이스에 위임하겠다는 전략
     * - MySQL 같은 경우, 데이터가 하나 들어갈 때 마다 PK 값이 자동으로 1 증가하여 들어감
     * - 개발자가 PK 지정해서 데이터베이스에 넣을 필요 없음
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String email;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    // 회원가입시 필요한 메소드
    public static User from(SignUpRequestDto request){
        return User.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .email(request.getEmail())
                .username(request.getUsername())
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
