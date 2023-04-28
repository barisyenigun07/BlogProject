package com.barisyenigun.blogserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileUploadException extends RuntimeException{
    public FileUploadException(){
        super("File Could Not Be Uploaded!");
    }
}
