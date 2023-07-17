package com.ootdgram.ootdgram.domain.dto;

import lombok.Getter;

@Getter
public class LikeResponseDto {
    private Long like;


    public LikeResponseDto (Long like) {
        this.like = getLike();
    }
}
