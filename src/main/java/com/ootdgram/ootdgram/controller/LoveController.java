package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.LoveRequestDto;
import com.ootdgram.ootdgram.security.UserDetailsImpl;
import com.ootdgram.ootdgram.service.LoveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "lovcontroller")
@RestController
@RequestMapping("/api/post")
public class LoveController {

    private final LoveService loveService;

    public LoveController(LoveService loveService) {
        this.loveService = loveService;
    }

    @PostMapping("/{postId}/love")
    public void clickLove(@PathVariable Long postId,
                                     @RequestBody LoveRequestDto loveRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        log.info("islove={}",loveRequestDto.isLove());
        loveService.clickLove(postId, loveRequestDto, userDetails.getUser());
    }
}
