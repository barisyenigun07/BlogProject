package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostCommentRequest {
    private String comment;
}
