package com.ootdgram.ootdgram.entity;

import com.ootdgram.ootdgram.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long postId;

//    @ManyToOne
//    @JoinColumn(name = "Post_Id", nullable = false)
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "User_Id", nullable = false)
//    private User user;

    public Comment(CommentRequestDto commentRequestDto, Long postId, String username) {
        this.content = commentRequestDto.getContent();
        this.username = commentRequestDto.getUsername();
        this.postId = postId;
    }

    public void update(CommentRequestDto commentRequestDt) {
        this.content = commentRequestDt.getContent();
        this.username = commentRequestDt.getUsername();
    }

}