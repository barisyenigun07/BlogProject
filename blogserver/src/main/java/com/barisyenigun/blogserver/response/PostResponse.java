package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String postType;
    private LocalDate publishedDate;
    private LocalDate modifiedDate;
    private TagResponse tagResponse;
    private UserResponse userResponse;

    public static PostResponse fromEntity(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .postType(post.getPostType())
                .publishedDate(post.getPublishedDate())
                .modifiedDate(post.getModifiedDate())
                .tagResponse(TagResponse.fromEntity(post.getTag()))
                .userResponse(UserResponse.fromEntity(post.getUser()))
                .build();
    }
}
