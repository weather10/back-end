package com.ootdgram.ootdgram.domain.dto;


import com.ootdgram.ootdgram.domain.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String image;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String nickname;
    private String userImage;
    private String weather;
    private int loveCount;
    public PostResponseDto(Post post){
        this.id = post.getId();
        this.image = post.getImage();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.nickname = post.getUser().getNickname();
        this.userImage = post.getUser().getImage();
        this.weather = post.getWeather();
        this.loveCount = post.getLoveCount();
    }
}
