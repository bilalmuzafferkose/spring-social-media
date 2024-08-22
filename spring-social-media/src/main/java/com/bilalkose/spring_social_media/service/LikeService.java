package com.bilalkose.spring_social_media.service;

import com.bilalkose.spring_social_media.dto.request.CreateLikeRequest;
import com.bilalkose.spring_social_media.dto.LikeDto;

import com.bilalkose.spring_social_media.exception.PostAlreadyLikedException;
import com.bilalkose.spring_social_media.model.Like;
import com.bilalkose.spring_social_media.model.mapper.LikeMapper;
import com.bilalkose.spring_social_media.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, LikeMapper likeMapper, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeDto> getAllByPost(Long postId) {
        List<Like> likeList = likeRepository.findAllByPostId(postId);
        return likeMapper.likesToLikeResponses(likeList);
    }

    public void save(CreateLikeRequest createLikeRequest) {
        checkIfIsExistUserByIdAndPostById(createLikeRequest);
        if (isLiked(createLikeRequest.getPostId())) {
            throw new PostAlreadyLikedException("This post is already liked by the user: " + createLikeRequest.getUserId());
        }
        Like like = likeMapper.requestToLike(createLikeRequest);
        likeRepository.save(like);
    }

    private void checkIfIsExistUserByIdAndPostById(CreateLikeRequest createLikeRequest) {
        userService.getUserById(createLikeRequest.getUserId());
        postService.getPostById(createLikeRequest.getPostId());
    }

    public boolean isLiked(Long postId) {
        List<Like> like = likeRepository.findAllByPostId(postId);
        return !like.isEmpty();
    }


}
