package com.barisyenigun.blogserver.request;


import com.barisyenigun.blogserver.entity.Tag;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
public class PostRequest {
    private String title;
    private String description;
    private MultipartFile captionPhoto;
    private String postType;
    private List<Tag> tags;
    private String articleContent;
    private MultipartFile mediaContent;
    private ZonedDateTime updatedDate = ZonedDateTime.now(ZoneId.systemDefault());
}
