package com.ootdgram.ootdgram.repository;

import com.ootdgram.ootdgram.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllById(Long postId);
//    Optional<Comment> findByCommentIdAndUsernameAndPostId(Long commentId, String username, Long postId);
}