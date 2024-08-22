package com.bilalkose.spring_social_media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AlreadyCreatedUserException extends RuntimeException {
    public AlreadyCreatedUserException(String message) {
        super(message);
    }
}