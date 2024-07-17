package com.example.demo.board.entity;

import com.example.demo.comment.domain.Comment;
import com.example.demo.board.dto.request.BoardRequest;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="userid")
    private User user;

    private LocalDateTime date;
    private String title;
    private String content;

    @OneToMany(mappedBy = "boardId")
    private List<Comment> comment;

    public static Board from(BoardRequest boardRequest){
        return Board.builder()
                .user(boardRequest.getUser())
                .date(LocalDateTime.now())
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .build();
    }

    @Builder
    public Board(User user, LocalDateTime date, String title, String content) {
        this.user = user;
        this.date = date;
        this.title = title;
        this.content = content;
    }
}