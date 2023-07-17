package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.security.UserDetailsImpl;
import com.ootdgram.ootdgram.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.ootdgram.ootdgram.domain.dto.UserDto.SignupRequest;
import static com.ootdgram.ootdgram.domain.dto.UserDto.UpdateRequest;

@Slf4j(topic = "Signup")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public void signup(@RequestBody @Valid SignupRequest requestDto)  {
        userService.signup(requestDto);
    }

    @PatchMapping("/auth/update")
    public void updateUser(@RequestPart("data") @Valid UpdateRequest requestDto,
                           @RequestPart("image") MultipartFile multipartFile,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        userService.update(requestDto, multipartFile, userDetails.getUser());
    }
}
