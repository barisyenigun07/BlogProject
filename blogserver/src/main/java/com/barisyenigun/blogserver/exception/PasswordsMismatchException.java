package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordsMismatchException extends RuntimeException{
    public PasswordsMismatchException(){
        super("Passwords Do Not Match!");
    }
}
