package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 2636936156391265891L;
    @NonNull
    private String username;
    @NonNull
    private String password;
}
