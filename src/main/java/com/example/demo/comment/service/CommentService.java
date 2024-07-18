package com.example.demo.comment.service;


import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.comment.dto.request.CommentRequestDto;
import com.example.demo.comment.dto.response.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 작성하기
    /*@Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto){
        Comment comment=comment

        Comment savedComment = commentRepository.save(comment);
        return savedComment
    }*/
}
