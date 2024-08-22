package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.FollowDto;
import com.bilalkose.spring_social_media.dto.request.CreateFollowRequest;
import com.bilalkose.spring_social_media.model.Follow;
import com.bilalkose.spring_social_media.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-22T21:22:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public FollowDto followToResponse(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        FollowDto.FollowDtoBuilder followDto = FollowDto.builder();

        followDto.followingId( followFollowingId( follow ) );
        followDto.followerId( followUserId( follow ) );
        followDto.id( follow.getId() );

        followDto.followingName( follow.getFollowing().getFullName() );
        followDto.followerName( follow.getUser().getFullName() );

        return followDto.build();
    }

    @Override
    public Follow addRequestToFollow(CreateFollowRequest createFollowRequest) {
        if ( createFollowRequest == null ) {
            return null;
        }

        Follow follow = new Follow();

        follow.setUser( createFollowRequestToUser( createFollowRequest ) );
        follow.setFollowing( createFollowRequestToUser1( createFollowRequest ) );

        return follow;
    }

    private Long followFollowingId(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User following = follow.getFollowing();
        if ( following == null ) {
            return null;
        }
        Long id = following.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long followUserId(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User user = follow.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User createFollowRequestToUser(CreateFollowRequest createFollowRequest) {
        if ( createFollowRequest == null ) {
            return null;
        }

        User user = new User();

        user.setId( createFollowRequest.getUserId() );

        return user;
    }

    protected User createFollowRequestToUser1(CreateFollowRequest createFollowRequest) {
        if ( createFollowRequest == null ) {
            return null;
        }

        User user = new User();

        user.setId( createFollowRequest.getFollowingId() );

        return user;
    }
}
