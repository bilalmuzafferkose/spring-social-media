package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.*;
import com.bilalkose.spring_social_media.dto.request.CreateLikeRequest;
import com.bilalkose.spring_social_media.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "postId",target = "post.id")
    @Mapping(source = "userId",target = "user.id")
    Like requestToLike(CreateLikeRequest createLikeRequest);

    List<LikeDto> likesToLikeResponses(List<Like> likes);
}