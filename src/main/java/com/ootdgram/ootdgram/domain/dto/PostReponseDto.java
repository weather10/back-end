package com.ootdgram.ootdgram.domain.dto;


import com.ootdgram.ootdgram.domain.entity.Post;
import lombok.Getter;

@Getter
public class PostReponseDto {
    private String image;

    private String content;

//    private List<CommentResponseDto> commentList = new ArrayList<>();
//    보류
//    private Weather weather;

    public PostReponseDto(Post post){
        this.image = post.getImage();
        this.content = post.getContent();
    }
//    public PostAllReponseDto(Post post){
//        this.image = post.getImage();
//        this.weather = Weather();
//    }
}
