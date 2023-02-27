package com.barisyenigun.blogserver.request;

import com.barisyenigun.blogserver.entity.PostType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ArticleRequest extends PostRequest{
    private String content;
    private PostType postType = PostType.ARTICLE;
}
