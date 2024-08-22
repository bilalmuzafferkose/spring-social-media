package com.bilalkose.spring_social_media.controller;

import com.bilalkose.spring_social_media.dto.request.CreateLikeRequest;
import com.bilalkose.spring_social_media.dto.LikeDto;
import com.bilalkose.spring_social_media.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/getAllByPost/{postId}")
    public ResponseEntity<List<LikeDto>> getAllByPost(@PathVariable Long postId){
        return ResponseEntity.ok(likeService.getAllByPost(postId));
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody CreateLikeRequest createLikeRequest){
        likeService.save(createLikeRequest);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    @GetMapping("/isliked")
    public ResponseEntity<Boolean> isLiked(@RequestParam Long postId){
        return new ResponseEntity<>(likeService.isLiked(postId),HttpStatus.OK);
    }
}
