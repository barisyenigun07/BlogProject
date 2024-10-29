package com.barisyenigun.blogserver.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private UserResponse user;
}
