package com.barisyenigun.blogserver.request;


import com.barisyenigun.blogserver.entity.Tag;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@ToString
public class PostRequest {
    private String title;
    private String description;
    private MultipartFile captionPhoto;
    private Tag tag;
    private String postType;
    private LocalDate updatedDate = LocalDate.now();
}
