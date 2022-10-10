package com.barisyenigun.blogserver.request.post;

import com.barisyenigun.blogserver.entity.PostType;
import com.barisyenigun.blogserver.entity.Tag;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class PostRequest {
    private String title;
    private String content;
    private Tag tag;
    private PostType type;
}
