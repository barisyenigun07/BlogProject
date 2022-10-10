package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post with that credentials not found!")
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(){
        super("Post Not Found!");
    }
}
