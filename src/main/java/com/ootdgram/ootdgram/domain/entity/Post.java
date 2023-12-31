package com.ootdgram.ootdgram.domain.entity;

import com.ootdgram.ootdgram.domain.dto.LoveRequestDto;
import com.ootdgram.ootdgram.domain.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "weather", nullable = false)
    private String weather;

    @Column(name = "love_count", nullable = false)
    @ColumnDefault("0")
    private int loveCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(PostRequestDto requestDto, User user, String imageURL) {
        this.content = requestDto.getContent();
        this.user = user;
        this.image = imageURL;
        this.weather = requestDto.getWeather();
    }

    public void update(PostRequestDto requestDto, String imageURL) {
        this.content = requestDto.getContent();
        this.image = imageURL;
    }

    public void updateLoveCount(LoveRequestDto requestDto) {
        if (requestDto.isLove()) {
            this.loveCount++;
        } else {
            this.loveCount--;
        }
    }
}
