package com.bilalkose.spring_social_media.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private List<Long> postIds;
}
