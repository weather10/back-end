package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.CommentRequestDto;
import com.ootdgram.ootdgram.domain.dto.CommentResponseDto;
import com.ootdgram.ootdgram.domain.dto.ResponseDto;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.security.UserDetailsImpl;
import com.ootdgram.ootdgram.security.jwt.JwtUtil;
import com.ootdgram.ootdgram.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getUser());
    }

    @GetMapping("/{postId}/comments")
    public List<CommentResponseDto> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PatchMapping("/{postId}/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseDto deleteComment(@PathVariable Long commentId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }
}//test