package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.Rate;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String captionPhotoLink;
    private String content;
    private String postType;
    private ZonedDateTime publishedDate;
    private ZonedDateTime modifiedDate;
    private double averageRate;
    private List<TagResponse> tags;
    private List<CommentResponse> comments;
    private UserResponse user;

    public static PostResponse fromEntity(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .captionPhotoLink(post.getCaptionPhotoLink())
                .content(post.getContent())
                .postType(post.getPostType())
                .publishedDate(post.getPublishedDate())
                .modifiedDate(post.getModifiedDate())
                .averageRate(Math.round(post.getRates().stream().mapToDouble(Rate::getRateLevel).average().orElse(0.0) * 2) / 2.0)
                .tags(post.getTags().stream().map(TagResponse::fromEntity).collect(Collectors.toList()))
                .comments(post.getComments().stream().map(CommentResponse::fromEntity).sorted(Comparator.comparing(CommentResponse::getPublishedDate).reversed()).collect(Collectors.toList()))
                .user(UserResponse.fromEntity(post.getUser()))
                .build();
    }
}
