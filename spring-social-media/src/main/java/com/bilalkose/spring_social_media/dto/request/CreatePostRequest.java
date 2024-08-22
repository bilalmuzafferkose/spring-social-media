package com.bilalkose.spring_social_media.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePostRequest {
    @Size(max = 200)
    private String description;
    @NotNull
    private Long userId;
}
