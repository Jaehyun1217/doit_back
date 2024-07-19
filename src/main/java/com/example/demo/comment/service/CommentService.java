package com.example.demo.comment.service;


import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.comment.dto.request.CommentRequestDto;
import com.example.demo.comment.dto.response.CommentResponseDto;
import com.example.demo.user.entity.User;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //댓글 작성하기
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Long boardId ,org.springframework.security.core.userdetails.User resUser){
        commentRequestDto.setUser(getUser(resUser));
        commentRequestDto.setBoard(getBoard(boardId));
        Comment savedComment = commentRepository.save(Comment.from(commentRequestDto));
        return new CommentResponseDto(savedComment);
    }

    //댓글 수정하기
    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Long commentId, org.springframework.security.core.userdetails.User resUser){
       Comment comment=commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("댓글이 없습니다."));

        User user=getUser(resUser);
        if(!commentRequestDto.getUser().getUsername().equals(user.getUsername())){
            throw new RuntimeException("수정 권한이 없습니다.");
        }else{
            comment.setComment(commentRequestDto.getComment());
            Comment updatedeComment=commentRepository.save((comment));

            return new CommentResponseDto((updatedeComment));
        }
    }

    //댓글 삭제하기
    @Transactional
    public CommentResponseDto deleteComment(Long commentId, org.springframework.security.core.userdetails.User resUser){
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("댓글이 없습니다."));

        User user = getUser(resUser);
        if(Objects.equals(comment.getUser().getUsername(), user.getUsername())){
            commentRepository.delete(comment);
            return CommentResponseDto.builder()
                    .build();
        }else{
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

    }

    //user 정보 조회
    private com.example.demo.user.entity.User getUser(org.springframework.security.core.userdetails.User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            return userRepository.findByUsername(user.getUsername()).get();
        }else{
            throw new UserNotFoundException();
        }
    }

    //board 정보 조회
    private Board getBoard(Long boardId){
        if(boardRepository.findById(boardId).isPresent()){
            return boardRepository.findById(boardId).get();
        }else{
            throw new RuntimeException("게시물이 없습니다.");
        }
    }
}
