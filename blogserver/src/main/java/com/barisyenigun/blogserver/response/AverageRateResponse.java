package com.barisyenigun.blogserver.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AverageRateResponse {
    private double averageRateLevel;
    private Long postId;
}
