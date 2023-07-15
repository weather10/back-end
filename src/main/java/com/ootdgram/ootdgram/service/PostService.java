package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import com.ootdgram.ootdgram.domain.dto.PostResponseDto;
import com.ootdgram.ootdgram.domain.dto.ResponseDto;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    public List<PostResponseDto> getAllPost() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 postId 입니다."));
        return new PostResponseDto(post);
    }

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findPost(postId);
        isWriterValidation(post, user);

        post.update(requestDto);

        return new PostResponseDto(post);
    }

    public ResponseDto deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 postId 입니다."));
        isWriterValidation(post, user);

        postRepository.deleteById(postId);

        return new ResponseDto(HttpStatus.OK.toString());
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 postId 입니다."));
    }

    private void isWriterValidation(Post post, User user) {
        if(!post.getUser().getEmail().equals(user.getEmail()))
            throw new IllegalArgumentException("작성자가 아닙니다.");
    }
}
