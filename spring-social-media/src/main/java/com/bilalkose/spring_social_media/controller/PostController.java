package com.bilalkose.spring_social_media.controller;

import com.bilalkose.spring_social_media.dto.request.CreatePostRequest;
import com.bilalkose.spring_social_media.dto.PostDto;
import com.bilalkose.spring_social_media.dto.request.PostRequestDto;
import com.bilalkose.spring_social_media.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostDto>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getAllByUser(userId));
    }

    @PostMapping("/save")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        return ResponseEntity.ok(postService.save(createPostRequest));
    }

    @PostMapping("/getPosts")
    public ResponseEntity<List<PostDto>> getPosts(@Valid @RequestBody PostRequestDto postRequestDto) {
        Long userId = postRequestDto.getUserId();
        List<Long> postIds = postRequestDto.getPostIds();
        List<PostDto> postDtoList = postService.getPosts(userId, postIds);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @PostMapping("/mixByOwners")
    public ResponseEntity<List<PostDto>> mixByOwners(@RequestBody List<PostDto> postDtoList) {
        List<PostDto> postDtoList1 = postService.mixByOwners(postDtoList);
        return new ResponseEntity<>(postDtoList1, HttpStatus.OK);
    }

    @PostMapping("/mergePosts")
    public ResponseEntity<List<PostDto>> mergePosts(@RequestBody List<List<PostDto>> listOfPostDtoList) {
        return ResponseEntity.ok(postService.mergePosts(listOfPostDtoList));
    }
}
