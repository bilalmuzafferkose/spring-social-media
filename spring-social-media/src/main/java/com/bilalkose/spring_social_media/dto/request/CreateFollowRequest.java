package com.bilalkose.spring_social_media.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFollowRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long followingId;
}
