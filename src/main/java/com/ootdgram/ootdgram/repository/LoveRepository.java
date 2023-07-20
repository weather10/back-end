package com.ootdgram.ootdgram.repository;

import com.ootdgram.ootdgram.domain.entity.Love;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoveRepository extends JpaRepository<Love, Long> {
    Optional<Love> findLoveByPostIdAndUserId(Long postId, Long userId);
    List<Love> findAll();
}
