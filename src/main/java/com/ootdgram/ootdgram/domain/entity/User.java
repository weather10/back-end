package com.ootdgram.ootdgram.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.ootdgram.ootdgram.domain.dto.UserDto.UpdateRequest;

@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String image;

    public User(String nickname, String email, String password, String imageUrl) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.image = imageUrl;
    }

    public void update(UpdateRequest requestDto, String url) {
        this.nickname = requestDto.getNickname();
        this.image = url;
    }
}
