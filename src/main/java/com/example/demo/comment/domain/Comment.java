package com.example.demo.comment.domain;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.dto.request.CommentRequestDto;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    private User user;
    private String comment;
    private LocalDateTime createComment;
    private  LocalDateTime updateComment;

    public static Comment from(CommentRequestDto commentRequestDto){
        return Comment.builder()
                .board(commentRequestDto.getBoard())
                .user(commentRequestDto.getUser())
                .comment(commentRequestDto.getComment())
                .build();
    }

    @Builder
    public Comment(User user, String comment, Board board,LocalDateTime createComment, LocalDateTime updateComment) {
        this.user = user;
        this.comment = comment;
        this.board = board;
        this.createComment=createComment;
        this.updateComment=updateComment;
    }
}
