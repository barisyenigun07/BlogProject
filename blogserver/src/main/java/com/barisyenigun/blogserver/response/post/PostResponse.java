package com.barisyenigun.blogserver.response.post;

import com.barisyenigun.blogserver.response.tag.TagResponse;
import com.barisyenigun.blogserver.response.user.UserResponse;
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
