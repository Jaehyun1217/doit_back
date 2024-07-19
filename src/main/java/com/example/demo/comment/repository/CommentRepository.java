package com.example.demo.comment.repository;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.domain.Comment;
import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<User> findByUser(User user);
    Optional<Board> findByBoard(Board board);
}
