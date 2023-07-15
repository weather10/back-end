package com.ootdgram.ootdgram.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long commentId;
    private String username;
    private String content;
}
