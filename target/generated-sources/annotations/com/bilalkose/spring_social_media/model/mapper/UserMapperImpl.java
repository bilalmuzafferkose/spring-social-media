package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.UserDto;
import com.bilalkose.spring_social_media.dto.request.CreateUserRequest;
import com.bilalkose.spring_social_media.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-22T21:22:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
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
    public User toModel(CreateUserRequest createUserRequest) {
        if ( createUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( createUserRequest.getUsername() );
        user.setEmail( createUserRequest.getEmail() );
        user.setPassword( createUserRequest.getPassword() );
        user.setFullName( createUserRequest.getFullName() );
        user.setProfilePicture( createUserRequest.getProfilePicture() );
        user.setBio( createUserRequest.getBio() );

        return user;
    }
}
