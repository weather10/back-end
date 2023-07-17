package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import com.ootdgram.ootdgram.domain.dto.PostResponseDto;
import com.ootdgram.ootdgram.domain.dto.ResponseDto;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.PostRepository;
import com.ootdgram.ootdgram.util.AwsS3Util;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class PostService {

    private final PostRepository postRepository;
    private final AwsS3Util awsS3Util;
    private final String dirName = "post";

    public PostService(PostRepository postRepository, AwsS3Util awsS3Util) {
        this.postRepository = postRepository;
        this.awsS3Util = awsS3Util;
    }

    public List<PostResponseDto> getAllPost() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 postId 입니다."));
        return new PostResponseDto(post);
    }

    public PostResponseDto createPost(PostRequestDto requestDto, MultipartFile multipartFile, User user) {
        String imageURL = awsS3Util.upload(multipartFile, dirName);

        Post post = new Post(requestDto, user, imageURL);
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId,
                                      PostRequestDto requestDto,
                                      MultipartFile multipartFile,
                                      User user) {
        Post post = findPost(postId);

        isWriterValidation(post, user);

        String updateImageURL = awsS3Util.update(multipartFile, post.getImage(), dirName);
        post.update(requestDto, updateImageURL);

        return new PostResponseDto(post);
    }

    public ResponseDto deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 postId 입니다."));

        isWriterValidation(post, user);

        awsS3Util.delete(post.getImage());
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
