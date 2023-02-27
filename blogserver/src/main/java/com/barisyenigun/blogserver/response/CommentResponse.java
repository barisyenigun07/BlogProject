package com.barisyenigun.blogserver.response;

import com.barisyenigun.blogserver.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class CommentResponse {
    private Long id;
    private String comment;
    private PostResponse postResponse;
    private UserResponse userResponse;

    public static CommentResponse fromEntity(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .postResponse(PostResponse.fromEntity(comment.getPost()))
                .userResponse(UserResponse.fromEntity(comment.getUser()))
                .build();
    }
}
