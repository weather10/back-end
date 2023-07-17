package com.ootdgram.ootdgram.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserDto {
    @Getter
    @NoArgsConstructor
    public static class SignupRequest {
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{5,12}$")
        private String nickname;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
        private String email;

        @NotBlank
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,21}$")
        private String password;
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        @NotBlank
        private String nickname;
    }
}
