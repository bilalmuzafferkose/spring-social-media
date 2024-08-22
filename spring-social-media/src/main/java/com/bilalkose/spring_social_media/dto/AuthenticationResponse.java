package com.bilalkose.spring_social_media.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String token;
    public AuthenticationResponse(String token) {
        this.token = token;
    }

}
