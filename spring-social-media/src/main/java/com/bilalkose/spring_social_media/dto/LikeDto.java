package com.bilalkose.spring_social_media.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LikeDto {
    private PostDto postDto;
    private UserDto userDto;
}
