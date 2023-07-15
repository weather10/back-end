package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.PostReponseDto;
import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import com.ootdgram.ootdgram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //    게시글 조회	GET	/api/post
    @GetMapping("/post")
    public List<PostReponseDto> getAllPost() {
        return postService.getAllPost();
    }
    //    게시글 단일 조회	GET	/api/post/{id}
    @GetMapping("/post/{postId}")
    public PostReponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
    //    게시글 작성 	POST	/api/post
    @PostMapping("/post")
    public PostReponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }
    //    게시글 수정	Put	/api/post/{id}
    @PutMapping("/post/{postId}")
    public PostReponseDto updataPost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(postId, requestDto);
    }
    //    게시글 삭제	Delete	/api/post/{id}
    @DeleteMapping("/post/{postId}")
    public PostReponseDto deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }
}
