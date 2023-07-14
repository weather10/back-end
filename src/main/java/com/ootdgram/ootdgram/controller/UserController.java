package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.SignupRequestDto;
import com.ootdgram.ootdgram.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup();
    }
}
