package com.example.demo.comment.dto.response;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.domain.Comment;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CommentResponseDto {
    Long commentId;
    Board boardId;
    User user;
    String comment;

    @Builder
    public CommentResponseDto(Comment comment){
        this.commentId=comment.getId();
        this.boardId=comment.getBoard();
        this.user=comment.getUser();
        this.comment=comment.getComment();
    }

}