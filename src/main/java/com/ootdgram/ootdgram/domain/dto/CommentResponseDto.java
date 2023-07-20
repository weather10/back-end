package com.ootdgram.ootdgram.domain.dto;

import com.ootdgram.ootdgram.domain.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String userImage;

    public CommentResponseDto (Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.nickname = comment.getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.userImage = comment.getUser().getImage();
    }
}