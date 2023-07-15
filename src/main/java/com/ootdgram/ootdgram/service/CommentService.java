package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.dto.CommentRequestDto;
import com.ootdgram.ootdgram.dto.CommentResponseDto;
import com.ootdgram.ootdgram.dto.ResponseDto;
import com.ootdgram.ootdgram.entity.Comment;
import com.ootdgram.ootdgram.jwt.JwtUtil;
import com.ootdgram.ootdgram.repository.CommentRepository;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public CommentService(CommentRepository commentRepository, JwtUtil jwtUtil) {
//        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.jwtUtil = jwtUtil;
    }

    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto,String token) {
        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token1);
        String username = info.getSubject();

        Comment comment = new Comment(commentRequestDto, postId, username);

        Comment saveComment = commentRepository.save(comment);
        return new CommentResponseDto(saveComment);
    }

    public List<CommentResponseDto> getComments(Long postId,String token) {
        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        List<Comment> commentList = commentRepository.findAllById(postId);
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto1 = new CommentResponseDto(comment);
            commentResponseDto.add(commentResponseDto1);
        }
        return commentResponseDto;
    }

    public CommentResponseDto updateComment(Long postId,Long commentId, CommentRequestDto commentRequestDto,String token) {
        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token1);
        String username = info.getSubject();

        Comment comment = findComment(commentId, username, postId);


        comment.update(commentRequestDto);

        return new CommentResponseDto(comment);
    }


    public ResponseDto deleteComment(Long postId, Long commentId,String token) {
        String token1 = jwtUtil.substringToken(token);

        if (!jwtUtil.validateToken(token1)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);
        String username = info.getSubject();

        Comment comment = findComment(commentId, username, postId);

        commentRepository.delete(comment);

        return new ResponseDto("삭제완료");
    }

    private Comment findComment(Long commentId, String username, Long postId) {
//        return commentRepository.findByCommentIdAndUsernameAndPostId(commentId, username, postId).orElseThrow(
//                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
//        );
        return null;
    }

//    private Post findPost(Long postId) {
//        return postRepository.findById(postId).orElseThrow(() ->
//                new IllegalArgumentException("존재하지 않는 포스트입니다.")
//        );
//    }
//
//    public User findUser(String username) {
//        return userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
//        );
//    }
}