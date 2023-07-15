package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.PostReponseDto;
import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    public List<PostReponseDto> getAllPost() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostReponseDto::new).toList();
    }

    public PostReponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 postId 입니다."));
        return new PostReponseDto(post);
    }

    public PostReponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostReponseDto(post);
    }

    @Transactional
    public PostReponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 postId 입니다."));
        post.update(requestDto);
        return new PostReponseDto(post);
    }

    public PostReponseDto deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 postId 입니다."));
        postRepository.deleteById(postId);
        return new PostReponseDto(post);
    }
}
