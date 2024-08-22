package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.PostDto;
import com.bilalkose.spring_social_media.dto.UserDto;
import com.bilalkose.spring_social_media.dto.request.CreatePostRequest;
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
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toModel(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postDto.getId() );
        post.setDescription( postDto.getDescription() );
        post.setLikedByUser( postDto.isLikedByUser() );
        post.setCreatedAt( postDto.getCreatedAt() );

        return post;
    }

    @Override
    public Post toModel(CreatePostRequest createPostRequest) {
        if ( createPostRequest == null ) {
            return null;
        }

        Post post = new Post();

        post.setDescription( createPostRequest.getDescription() );

        return post;
    }

    @Override
    public List<PostDto> toDtoList(List<Post> postList) {
        if ( postList == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( postList.size() );
        for ( Post post : postList ) {
            list.add( toDtoWithUser( post ) );
        }

        return list;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.username( user.getUsername() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.fullName( user.getFullName() );
        userDto.profilePicture( user.getProfilePicture() );
        userDto.bio( user.getBio() );
        userDto.followedByUser( user.isFollowedByUser() );

        return userDto.build();
    }

    @Override
    public PostDto toDtoWithUser(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto.PostDtoBuilder postDto = PostDto.builder();

        postDto.id( post.getId() );
        postDto.description( post.getDescription() );
        postDto.likedByUser( post.isLikedByUser() );
        postDto.createdAt( post.getCreatedAt() );

        postDto.userDto( post.getUser() != null ? toUserDto(post.getUser()) : null );

        return postDto.build();
    }
}
