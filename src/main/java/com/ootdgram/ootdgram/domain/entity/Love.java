package com.ootdgram.ootdgram.domain.entity;

import com.ootdgram.ootdgram.domain.dto.LoveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="love")
@NoArgsConstructor
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Love(LoveRequestDto requestDto, User user, Post post){
        this.post = post;
        this.user = user;
    }



}
