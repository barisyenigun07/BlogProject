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
    private String content;
    private UserResponse userResponse;

    public static CommentResponse fromEntity(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userResponse(UserResponse.fromEntity(comment.getUser()))
                .build();
    }
}
