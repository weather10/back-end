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
    private List<CommentResponseDto> commentList;
    private String nickname;
    public PostResponseDto(Post post){
        this.id = post.getId();
        this.image = post.getImage();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new).toList();
        this.nickname = post.getUser().getNickname();
    }
}
