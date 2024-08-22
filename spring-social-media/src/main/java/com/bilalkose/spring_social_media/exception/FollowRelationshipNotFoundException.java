package com.bilalkose.spring_social_media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FollowRelationshipNotFoundException extends RuntimeException {
    public FollowRelationshipNotFoundException(String message) {
        super(message);
    }
}
