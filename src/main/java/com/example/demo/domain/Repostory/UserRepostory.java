package com.example.demo.domain.Repostory;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepostory extends JpaRepository<User, UUID> {
    Optional<User> findByUserId(String userId);
}
