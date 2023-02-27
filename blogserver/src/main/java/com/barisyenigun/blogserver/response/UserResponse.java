package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.User;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String username;

    public static UserResponse fromEntity(User user){
        if (user == null){
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
