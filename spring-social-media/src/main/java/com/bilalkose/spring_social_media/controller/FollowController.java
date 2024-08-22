package com.bilalkose.spring_social_media.controller;

import com.bilalkose.spring_social_media.dto.request.CreateFollowRequest;
import com.bilalkose.spring_social_media.service.FollowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/follow")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/add") //follow
    public ResponseEntity<String> add(@Valid @RequestBody CreateFollowRequest createFollowRequest){
        followService.add(createFollowRequest);
        return new ResponseEntity<>("Followed", HttpStatus.OK);
    }

    @PostMapping("/delete") //unfollow
    public ResponseEntity<String> delete(@Valid @RequestBody CreateFollowRequest  followRequest){
        followService.delete(followRequest);
        return new ResponseEntity<>("Unfollowed",HttpStatus.OK);
    }
}
