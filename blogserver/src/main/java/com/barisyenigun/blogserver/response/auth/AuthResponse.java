package com.barisyenigun.blogserver.response.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponse {
    private final String token;
}
