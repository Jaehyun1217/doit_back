package com.example.demo.comment.domain;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.dto.request.CommentRequestDto;
import com.example.demo.common.entity.BaseTimeEntity;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String comment;

    public static Comment from(CommentRequestDto commentRequestDto){
        return Comment.builder()
                .board(commentRequestDto.getBoard())
                .user(commentRequestDto.getUser())
                .comment(commentRequestDto.getComment())
                .build();
    }

    @Builder
    public Comment(User user, String comment, Board board) {
        this.user = user;
        this.comment = comment;
        this.board = board;
    }
}
