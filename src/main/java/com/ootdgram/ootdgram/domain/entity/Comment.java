package com.ootdgram.ootdgram.domain.entity;

import com.ootdgram.ootdgram.domain.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, User user, Post post) {
        this.content = requestDto.getContent();
        this.nickname = user.getNickname();
        this.user = user;
        this.post = post;
        post.getCommentList().add(this);
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}