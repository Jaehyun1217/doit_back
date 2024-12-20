package com.example.demo.board.service;

import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.common.response.ResponseDTO;
import com.example.demo.board.dto.request.BoardRequest;
import com.example.demo.board.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final User user;

    //게시판 전체보기 (날짜기준 내림차순)
    @Transactional(readOnly = true)
    public List<BoardResponse> getPost(){
        return boardRepository.findAllByOrderByDateDesc()
                .stream()
                .map(BoardResponse::new)
                .toList();
    }

    //게시글 작성
    @Transactional
    public BoardResponse createPost(BoardRequest boardRequest){
        Board board=Board.from(boardRequest);
        boardRequest.setUser();
        boardRepository.save(board); //데이터베이스에 저장
        return new BoardResponse(board); //프론트에 엔티티 전송
    }

    //특정 게시글 보기
    @Transactional
    public ResponseDTO boardview(Long boardid){
        if(boardRepository.findById(boardid).isPresent()){
            Board board = boardRepository.findById(boardid).get();

            return ResponseDTO.builder()
                    .message("게시글을 불러왔습니다.")
                    .data(board)
                    .build();

        }
        else throw new RuntimeException("찾을 수 없는 게시글입니다.");
    }

    //게시글 수정
    @Transactional
    public ResponseDTO updatePost(Long boardid, BoardRequest boardRequest){
        if(boardRepository.findById(boardid).isPresent()){
            Board board = boardRepository.findById(boardid).get(); //특정 보드 가져오기
            if(Objects.equals(board.getUser(), boardRequest.getUser())){
                boardRequest.setUser();
                board.setTitle(boardRequest.getTitle());
                board.setContent(boardRequest.getContent());
                boardRepository.save(board);
                return ResponseDTO.builder()
                        .message("게시글이 저장되었습니다.")
                        .build();
            }else {
                throw new RuntimeException("작성한 게시글이 없습니다.");
            }
        }
        else throw new RuntimeException("찾을 수 없는 게시글입니다.");
    }

    //게시글 삭제
    @Transactional
    public ResponseDTO deleteBoard(Long boardId, User user){
        if(boardRepository.findById(boardId).isPresent()){
            Board board=boardRepository.findById(boardId).get();
            if(Objects.equals(board.getUser().getUserId(), user.getUsername())) {
                boardRepository.delete(board);
                return ResponseDTO.builder()
                        .message("게시글이 삭제되었습니다.")
                        .build();
            }else {
                throw new RuntimeException("게시글 삭제 권한이 없습니다.");
            }
        }
        else throw new RuntimeException("찾을 수 없는 게시물 입니다.");
    }
}