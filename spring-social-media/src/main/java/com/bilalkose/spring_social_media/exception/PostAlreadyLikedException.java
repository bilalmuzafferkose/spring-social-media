package com.bilalkose.spring_social_media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PostAlreadyLikedException extends RuntimeException {
    public PostAlreadyLikedException(String message) {
        super(message);
    }
}
