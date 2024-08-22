package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.LikeDto;
import com.bilalkose.spring_social_media.dto.request.CreateLikeRequest;
import com.bilalkose.spring_social_media.model.Like;
import com.bilalkose.spring_social_media.model.Post;
import com.bilalkose.spring_social_media.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-22T21:22:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LikeMapperImpl implements LikeMapper {

    @Override
    public Like requestToLike(CreateLikeRequest createLikeRequest) {
        if ( createLikeRequest == null ) {
            return null;
        }

        Like like = new Like();

        like.setPost( createLikeRequestToPost( createLikeRequest ) );
        like.setUser( createLikeRequestToUser( createLikeRequest ) );

        return like;
    }

    @Override
    public List<LikeDto> likesToLikeResponses(List<Like> likes) {
        if ( likes == null ) {
            return null;
        }

        List<LikeDto> list = new ArrayList<LikeDto>( likes.size() );
        for ( Like like : likes ) {
            list.add( likeToLikeDto( like ) );
        }

        return list;
    }

    protected Post createLikeRequestToPost(CreateLikeRequest createLikeRequest) {
        if ( createLikeRequest == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( createLikeRequest.getPostId() );

        return post;
    }

    protected User createLikeRequestToUser(CreateLikeRequest createLikeRequest) {
        if ( createLikeRequest == null ) {
            return null;
        }

        User user = new User();

        user.setId( createLikeRequest.getUserId() );

        return user;
    }

    protected LikeDto likeToLikeDto(Like like) {
        if ( like == null ) {
            return null;
        }

        LikeDto.LikeDtoBuilder likeDto = LikeDto.builder();

        return likeDto.build();
    }
}
