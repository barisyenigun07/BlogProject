package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

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
    private double averageRate;
    private List<TagResponse> tags;
    private UserResponse user;
}
