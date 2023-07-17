package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.LikeRequestDto;
import com.ootdgram.ootdgram.domain.dto.LikeResponseDto;
import com.ootdgram.ootdgram.domain.entity.Like_post;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.LikeRepository;
import com.ootdgram.ootdgram.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public LikeService(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public LikeResponseDto addLike(Long postId, LikeRequestDto likeRequestDto, User user) {
        Post findPost = findPost(postId);
        Like_post like = new Like_post(likeRequestDto, user, findPost);

        like.increaseLikeCount();
        Like_post saveLike = likeRepository.save(like);

        return new LikeResponseDto(saveLike.getLikeCount());
    }

    public LikeResponseDto subtractLike(Long postId, LikeRequestDto likeRequestDto, User user){
        Post findPost = findPost(postId);
        Like_post like = new Like_post(likeRequestDto, user, findPost);

        like.decreaseLikeCount();
        Like_post saveLike = likeRepository.save(like);

        return new LikeResponseDto(saveLike.getLikeCount());
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
    }
}
