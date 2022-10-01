package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateUserRequest {
    private String name;
    private String profilePhotoLink;
    private String username;
}
