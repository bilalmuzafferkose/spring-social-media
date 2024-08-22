package com.bilalkose.spring_social_media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SameFollowingUserException extends RuntimeException {
    public SameFollowingUserException(String message) {
        super(message);
    }
}
