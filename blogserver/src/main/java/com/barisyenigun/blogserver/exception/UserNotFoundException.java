package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User with that credentials not found!")
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User Not Found!");
    }
}
