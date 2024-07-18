package com.example.demo.board.controller;

import com.example.demo.user.repository.UserRepository;
import com.example.demo.common.response.ResponseDTO;
import com.example.demo.board.dto.request.BoardRequest;
import com.example.demo.board.dto.response.BoardResponse;
import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final UserRepository userRepository;

    //게시판 전체보기 (내림차순)
    @GetMapping("/board")
    public ResponseEntity<List<BoardResponse>> getPost(){
        return ResponseEntity.ok().body(boardService.getPost());
    }

    //게시판 작성하기
    @PostMapping("/createpost")
    public ResponseEntity<BoardResponse> createPost(@RequestBody BoardRequest boardRequest,@AuthenticationPrincipal User user){
        return ResponseEntity.created(URI.create("/createpost")).body(boardService.createPost(boardRequest, user));
    }

    //특정 게시글 보기
        @GetMapping("/boardview/{boardid}")
    public ResponseEntity<ResponseDTO> boardview(@PathVariable("boardid") Long boardid){
        return ResponseEntity.ok().body(boardService.boardview(boardid));
    }

    //게시판 수정하기
    @PostMapping("/updatepost/{boardid}")
    public ResponseEntity<ResponseDTO> updatePost(@PathVariable("boardid") Long boardId, @RequestBody BoardRequest boardRequest, @AuthenticationPrincipal User user){
        return ResponseEntity.created(URI.create("/updatepost/"+boardId)).body(boardService.updatePost(boardId,boardRequest, user));
    }

    //게시판 삭제하기
    @DeleteMapping("/deleteboard/{boardid}")
    public ResponseEntity<ResponseDTO> deleteboard(@PathVariable("boardid") Long boardid, @AuthenticationPrincipal User user){
        return ResponseEntity.created(URI.create("/deleteboard/"+boardid)).body(boardService.deleteBoard(boardid,user));
    }
}