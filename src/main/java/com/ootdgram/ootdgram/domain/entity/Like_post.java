package com.ootdgram.ootdgram.domain.entity;

import com.ootdgram.ootdgram.domain.dto.LikeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Like_post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private Long like;

    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Like_post(LikeRequestDto requestDto, User user, Post post){
        this.like = requestDto.getLike();
        this.post = post;
        this.user = user;
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public void decreaseLikeCount() {
        likeCount--;
    }

    public Long getLikeCount() {
        return likeCount;
    }
}
