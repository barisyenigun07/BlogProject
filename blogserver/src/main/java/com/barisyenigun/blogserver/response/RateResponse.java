package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Rate;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RateResponse {
    private Long id;
    private double rateLevel;
    private UserResponse user;

    public static RateResponse fromEntity(Rate rate){
        return RateResponse.builder()
                .id(rate.getId())
                .rateLevel(rate.getRateLevel())
                .user(UserResponse.fromEntity(rate.getUser()))
                .build();
    }
}
