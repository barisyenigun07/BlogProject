package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Passwords in database and entered do not match!")
public class PasswordsMismatchException extends RuntimeException{
    public PasswordsMismatchException(){
        super("Passwords do not match!");
    }
}
