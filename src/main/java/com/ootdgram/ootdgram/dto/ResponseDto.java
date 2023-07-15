package com.ootdgram.ootdgram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ResponseDto {
    private String msg;

    public ResponseDto(String msg){
        this.msg = msg;
    }
}