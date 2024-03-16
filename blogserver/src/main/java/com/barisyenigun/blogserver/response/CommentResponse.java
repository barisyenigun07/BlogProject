package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


import java.time.ZonedDateTime;

@Data
@ToString
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private ZonedDateTime publishedDate;
    private UserResponse user;
    private Long parentCommentId;

    public static CommentResponse fromEntity(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .publishedDate(comment.getPublishedDate())
                .user(UserResponse.fromEntity(comment.getUser()))
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .build();
    }
}
