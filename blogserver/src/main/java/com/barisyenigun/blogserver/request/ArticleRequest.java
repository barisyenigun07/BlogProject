package com.barisyenigun.blogserver.request;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ArticleRequest extends PostRequest{
    private String content;
}
