package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
public class UpdateUserRequest {
    private String name;
    private String username;
    private String email;
    private MultipartFile profilePhoto;
    private MultipartFile captionPhoto;
}
