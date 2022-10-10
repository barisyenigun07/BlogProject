package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "A user with that password already been existed!")
public class PasswordAlreadyTakenException extends RuntimeException{
    public PasswordAlreadyTakenException(){
        super("This password has already been taken!");
    }
}
