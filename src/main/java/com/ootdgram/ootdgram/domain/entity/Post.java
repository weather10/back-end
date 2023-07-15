package com.ootdgram.ootdgram.domain.entity;

import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "content", nullable = false)
    private String content;
//단방향 user와
//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    @OrderBy("createdAt DESC")
//    private List<Comment> commentList= new ArrayList<>();

    public Post(PostRequestDto requestDto) {
        this.image = requestDto.getImage();
        this.content = requestDto.getContent();
    }

    public void update(PostRequestDto requestDto) {
        this.image = requestDto.getImage();
        this.content = requestDto.getContent();
    }
}
