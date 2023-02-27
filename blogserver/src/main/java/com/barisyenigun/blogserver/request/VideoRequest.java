package com.barisyenigun.blogserver.request;

import com.barisyenigun.blogserver.entity.PostType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class VideoRequest extends PostRequest{
    private MultipartFile file;
    private PostType postType = PostType.VIDEO;
}
