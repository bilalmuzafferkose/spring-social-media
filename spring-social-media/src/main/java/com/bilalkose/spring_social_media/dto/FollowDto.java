package com.bilalkose.spring_social_media.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class FollowDto {
    private Long id;
    private Long followerId;
    private Long followingId;
    private String followerName;
    private String followingName;
}
