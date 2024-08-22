package com.bilalkose.spring_social_media.service;

import com.bilalkose.spring_social_media.dto.request.CreateFollowRequest;
import com.bilalkose.spring_social_media.exception.AlreadyFollowedException;
import com.bilalkose.spring_social_media.exception.FollowRelationshipNotFoundException;
import com.bilalkose.spring_social_media.exception.SameFollowingUserException;
import com.bilalkose.spring_social_media.model.Follow;
import com.bilalkose.spring_social_media.model.mapper.FollowMapper;
import com.bilalkose.spring_social_media.repository.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;
    private final FollowMapper followMapper;

    public FollowService(FollowRepository followRepository, UserService userService, FollowMapper followMapper) {
        this.followRepository = followRepository;
        this.userService = userService;
        this.followMapper = followMapper;
    }

    public void add(CreateFollowRequest createFollowRequest) {
        checkIfIsExistUserByIdAndFollowingById(createFollowRequest);
        checkIfIsSameFollowerAndFollowingUser(createFollowRequest);
        if (userService.isFollowing(createFollowRequest.getUserId(), createFollowRequest.getFollowingId())) {
            throw new AlreadyFollowedException("This user is already followed: " + createFollowRequest.getUserId());
        }
        followRepository.save(followMapper.addRequestToFollow(createFollowRequest));
    }

    private void checkIfIsExistUserByIdAndFollowingById(CreateFollowRequest createFollowRequest) {
        userService.getUserEntityById(createFollowRequest.getUserId());
        userService.getUserEntityById(createFollowRequest.getFollowingId());
    }

    private void checkIfIsSameFollowerAndFollowingUser(CreateFollowRequest createFollowRequest) {
        if(createFollowRequest.getUserId().equals(createFollowRequest.getFollowingId())) {
            throw new SameFollowingUserException("User ID and Following ID must not be the same " + createFollowRequest.getUserId());
        }
    }

    public void delete(CreateFollowRequest followRequest) {
        Follow follow = followRepository.findByUserIdAndFollowingId(followRequest.getUserId(), followRequest.getFollowingId())
                .orElseThrow(
                        () -> new FollowRelationshipNotFoundException("Follow relationship not found. ")
                );
        followRepository.delete(follow);
    }
}
