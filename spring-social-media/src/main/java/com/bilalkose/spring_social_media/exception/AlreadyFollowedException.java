package com.bilalkose.spring_social_media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyFollowedException extends RuntimeException {
    public AlreadyFollowedException(String message) {
        super(message);
    }
}
