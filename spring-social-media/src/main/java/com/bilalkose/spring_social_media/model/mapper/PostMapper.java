package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.request.CreatePostRequest;
import com.bilalkose.spring_social_media.dto.PostDto;
import com.bilalkose.spring_social_media.dto.UserDto;
import com.bilalkose.spring_social_media.model.Post;
import com.bilalkose.spring_social_media.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    //PostDto toDto(Post post);
    Post toModel(PostDto postDto);
    Post toModel(CreatePostRequest createPostRequest);
    List<PostDto> toDtoList(List<Post> postList);

    // User entity'den UserDto'ya mapping
    UserDto toUserDto(User user);

    // Custom dönüşüm metodu
    @Mapping(target = "userDto", expression = "java(post.getUser() != null ? toUserDto(post.getUser()) : null)")
    PostDto toDtoWithUser(Post post);
}