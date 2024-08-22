package com.bilalkose.spring_social_media.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String description;
    private UserDto userDto; //postun sahibi
    private boolean likedByUser;
    private Date createdAt;
}
