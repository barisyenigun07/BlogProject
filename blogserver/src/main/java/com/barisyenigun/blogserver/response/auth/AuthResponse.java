package com.barisyenigun.blogserver.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;
}
