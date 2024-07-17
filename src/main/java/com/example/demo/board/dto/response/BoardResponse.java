package com.example.demo.board.dto.response;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class BoardResponse {
    User user;
    LocalDateTime date;
    String title;
    String content;

    @Builder
    public BoardResponse(Board board){
        this.user = board.getUser();
        this.date=board.getDate();
        this.title=board.getTitle();
        this.content=board.getContent();
    }
}