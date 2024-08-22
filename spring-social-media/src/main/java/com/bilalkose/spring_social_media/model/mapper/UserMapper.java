package com.bilalkose.spring_social_media.model.mapper;

import com.bilalkose.spring_social_media.dto.request.CreateUserRequest;
import com.bilalkose.spring_social_media.dto.UserDto;
import com.bilalkose.spring_social_media.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toModel(CreateUserRequest createUserRequest);
}