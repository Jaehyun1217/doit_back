package com.example.demo.comment.controller;

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

    @PostMapping("/{boardid}")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable("commentId") Long boardId, @AuthenticationPrincipal User user){

        return ResponseEntity.created(URI.create("/comment/")).body(commentService.createComment(commentRequestDto, boardId ,user));
    }

}