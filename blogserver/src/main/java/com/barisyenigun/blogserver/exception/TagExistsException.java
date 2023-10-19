package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TagExistsException extends RuntimeException {
    public TagExistsException() {
        super("Tag With Given Name Exists!");
    }
}
