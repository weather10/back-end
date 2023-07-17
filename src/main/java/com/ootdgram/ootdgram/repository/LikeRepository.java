package com.ootdgram.ootdgram.repository;

import com.ootdgram.ootdgram.domain.entity.Like_post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like_post, Long> {
}
