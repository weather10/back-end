package com.ootdgram.ootdgram.dto;

import com.ootdgram.ootdgram.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;

    public CommentResponseDto (Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
    }
}