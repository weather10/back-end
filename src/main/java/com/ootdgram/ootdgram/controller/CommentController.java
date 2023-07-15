package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.dto.CommentRequestDto;
import com.ootdgram.ootdgram.dto.CommentResponseDto;
import com.ootdgram.ootdgram.dto.ResponseDto;
import com.ootdgram.ootdgram.jwt.JwtUtil;
import com.ootdgram.ootdgram.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comment")
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.createComment(postId, commentRequestDto, token);
    }

    @GetMapping("/{postId}/comment")
    public List<CommentResponseDto> getComments(@PathVariable Long postId, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.getComments(postId, token);
    }

    @PatchMapping("/{postId}/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.updateComment(postId, commentId, commentRequestDto, token);
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseDto deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        return commentService.deleteComment(postId, commentId, token);
    }
}