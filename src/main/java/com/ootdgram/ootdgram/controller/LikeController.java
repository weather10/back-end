package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.CommentRequestDto;
import com.ootdgram.ootdgram.domain.dto.LikeRequestDto;
import com.ootdgram.ootdgram.domain.dto.LikeResponseDto;
import com.ootdgram.ootdgram.security.UserDetailsImpl;
import com.ootdgram.ootdgram.service.LikeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{postId}/like")
    public LikeResponseDto addLike(@PathVariable Long postId,
                                   @RequestBody LikeRequestDto likeRequestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.addLike(postId, likeRequestDto, userDetails.getUser());
    }

    @PatchMapping("/{postId}/like")
    public LikeResponseDto subtractLike(@PathVariable Long postId,
                                        @RequestBody LikeRequestDto likeRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.subtractLike(postId, likeRequestDto, userDetails.getUser());
    }
}
