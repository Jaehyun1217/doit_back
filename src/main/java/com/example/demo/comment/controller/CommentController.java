package com.example.demo.comment.controller;

import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.dto.request.CommentRequestDto;
import com.example.demo.comment.dto.response.CommentResponseDto;
import com.example.demo.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/{boardid}")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal User user){

        return ResponseEntity.created(URI.create("/comment/")).body(commentService.createComment(commentRequestDto, boardId ,user));
    }

    //댓글 수정
    @PostMapping("/{boardid}/{commentid}")
    public  ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable("commentId") Long commentId, @AuthenticationPrincipal User user){
        CommentResponseDto updatedComment = commentService.updateComment(commentRequestDto,commentId,user);
        return ResponseEntity.ok(updatedComment);
    }

    //댓글 삭제
    @DeleteMapping("/{baordid}/{commentid}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal User user){
        commentService.deleteComment(commentId,user);
        return ResponseEntity.created(URI.create("/deletecomment/"+commentId)).body(commentService.deleteComment(commentId,user));
    }
}