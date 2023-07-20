package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.LoveRequestDto;
import com.ootdgram.ootdgram.domain.entity.Love;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.LoveRepository;
import com.ootdgram.ootdgram.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j(topic = "loveService")
@Service
public class LoveService {
    private final PostRepository postRepository;
    private final LoveRepository loveRepository;

    public LoveService(PostRepository postRepository, LoveRepository loveRepository) {
        this.postRepository = postRepository;
        this.loveRepository = loveRepository;
    }

    @Transactional
    public void clickLove(Long postId, LoveRequestDto loveRequestDto, User user) {
        Post post = findPost(postId);

        log.info("validate={}", isUserLovePostValidate(postId, user.getId()));
        if (!isUserLovePostValidate(postId, user.getId())) {
            if (loveRequestDto.isLove()) {
                Love love = new Love(loveRequestDto, user, post);
                loveRepository.save(love);
            }
        } else {
            if (!loveRequestDto.isLove()) {
                Love findLove = loveRepository.findLoveByPostIdAndUserId(postId, user.getId())
                        .orElseThrow(() -> new IllegalArgumentException("없습니다."));
                loveRepository.delete(findLove);
            }
        }
        post.updateLoveCount(loveRequestDto);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
    }

    private boolean isUserLovePostValidate(Long postId, Long userId) {
        Optional<Love> love = loveRepository.findLoveByPostIdAndUserId(postId, userId);
        return love.isPresent();
    }
}
