package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "A user with that username has already been existed!")
public class UsernameAlreadyTakenException extends RuntimeException{
    public UsernameAlreadyTakenException(){
        super("This username has already been taken!");
    }
}