package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Tag with given credentials not found!")
public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(){
        super("Tag not found!");
    }
}
