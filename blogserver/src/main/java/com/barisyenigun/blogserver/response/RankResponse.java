package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Rank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RankResponse {
    private Long id;
    private double rankLevel;
    private UserResponse userResponse;

    public static RankResponse fromEntity(Rank rank){
        return RankResponse.builder()
                .id(rank.getId())
                .rankLevel(rank.getRankLevel())
                .userResponse(UserResponse.fromEntity(rank.getUser()))
                .build();
    }
}
