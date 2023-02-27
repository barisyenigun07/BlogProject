package com.barisyenigun.blogserver.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RankResponse {
    private Long id;
    private double rankLevel;
    private PostResponse postResponse;
    private UserResponse userResponse;
}
