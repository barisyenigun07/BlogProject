package com.barisyenigun.blogserver.response.post;

import com.barisyenigun.blogserver.response.user.UserResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PostRankResponse {
    private Long id;
    private double rankLevel;
    private PostResponse postResponse;
    private UserResponse userResponse;
}
