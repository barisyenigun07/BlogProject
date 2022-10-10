package com.barisyenigun.blogserver.request.post;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostCommentRequest {
    private String comment;
}
