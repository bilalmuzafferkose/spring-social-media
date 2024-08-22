package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.request.CreateFollowRequest;
import com.bilalkose.spring_social_media.dto.FollowDto;
import com.bilalkose.spring_social_media.model.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Mapping(source = "following.id",target = "followingId")
    @Mapping(source = "user.id",target = "followerId")
    @Mapping(target = "followingName",expression = "java(follow.getFollowing().getFullName())")
    @Mapping(target = "followerName",expression = "java(follow.getUser().getFullName())")
    FollowDto followToResponse(Follow follow);

    @Mapping(source = "userId",target = "user.id")
    @Mapping(source = "followingId",target = "following.id")
    Follow addRequestToFollow(CreateFollowRequest createFollowRequest);

}
