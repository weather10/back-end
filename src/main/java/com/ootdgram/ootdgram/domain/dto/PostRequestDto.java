package com.ootdgram.ootdgram.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequestDto {
    @NotBlank
    private String content;

    @NotBlank
    private String weather;
}
