package com.barisyenigun.blogserver.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private TagResponse tagResponse;
    private UserResponse userResponse;
}
