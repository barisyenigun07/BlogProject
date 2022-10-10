package com.barisyenigun.blogserver.request.auth;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String passwordAgain;
}
