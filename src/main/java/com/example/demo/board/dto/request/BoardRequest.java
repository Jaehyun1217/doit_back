package com.example.demo.board.dto.request;

import com.example.demo.user.entity.User;
import lombok.Data;

@Data
public class BoardRequest {
    Long boardId;
    User user;
    String title;
    String content;
}