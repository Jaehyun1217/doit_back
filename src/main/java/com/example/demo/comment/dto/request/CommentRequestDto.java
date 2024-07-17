package com.example.demo.comment.dto.request;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Data;

@Data
public class CommentRequestDto {
    Board board;
    User user;
    String comment;
}
