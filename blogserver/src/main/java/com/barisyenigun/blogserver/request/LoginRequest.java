package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
@Data
@ToString
public class LoginRequest{
    @NonNull
    private String username;
    @NonNull
    private String password;
}