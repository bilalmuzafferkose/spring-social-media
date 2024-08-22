package com.bilalkose.spring_social_media.dto;


import lombok.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String profilePicture;
    private String bio;
    private List<PostDto> postDtoList;
    private boolean followedByUser;
}
