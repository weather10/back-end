package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.PostResponseDto;
import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import com.ootdgram.ootdgram.domain.dto.ResponseDto;
import com.ootdgram.ootdgram.security.UserDetailsImpl;
import com.ootdgram.ootdgram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //    게시글 조회	GET	/api/post
    @GetMapping("/posts")
    public List<PostResponseDto> getAllPost() {
        return postService.getAllPost();
    }
    //    게시글 단일 조회	GET	/api/post/{id}
    @GetMapping("/post/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
    //    게시글 작성 	POST	/api/post
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(requestDto, userDetails.getUser());
    }
    //    게시글 수정	Put	/api/post/{id}
    @PatchMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId,
                                      @RequestBody PostRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(postId, requestDto, userDetails.getUser());
    }
    //    게시글 삭제	Delete	/api/post/{id}
    @DeleteMapping("/post/{postId}")
    public ResponseDto deletePost(@PathVariable Long postId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(postId, userDetails.getUser());
    }
}
