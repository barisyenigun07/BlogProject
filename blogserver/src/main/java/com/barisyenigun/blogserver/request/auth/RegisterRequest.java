package com.barisyenigun.blogserver.request;

import com.sun.istack.NotNull;
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
}
