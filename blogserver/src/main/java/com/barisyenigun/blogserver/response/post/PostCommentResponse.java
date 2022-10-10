package com.barisyenigun.blogserver.response.post;

import com.barisyenigun.blogserver.response.user.UserResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PostCommentResponse {
    private Long id;
    private String comment;
    private PostResponse postResponse;
    private UserResponse userResponse;
}
