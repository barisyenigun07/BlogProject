package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {
    private String name;
    private String email;
    private String username;
    private String password;
    private String passwordRepeat;
}
