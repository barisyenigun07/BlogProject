package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.User;
import lombok.*;

@Data
@Builder
@ToString
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String profilePhotoLink;
    private String captionPhotoLink;

    public static UserResponse fromEntity(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .profilePhotoLink(user.getProfilePhotoLink())
                .captionPhotoLink(user.getCaptionPhotoLink())
                .build();
    }
}
