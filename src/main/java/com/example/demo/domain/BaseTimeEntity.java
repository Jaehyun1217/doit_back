package com.example.demo.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //상속받으면 필드들이 컴럼으로 인식 하도록 유도
@EntityListeners(AuditingEntityListener.class) //Auditing  기능 포함

public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}