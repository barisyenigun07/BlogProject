package com.barisyenigun.blogserver.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentRequest {
    private String content;
}
