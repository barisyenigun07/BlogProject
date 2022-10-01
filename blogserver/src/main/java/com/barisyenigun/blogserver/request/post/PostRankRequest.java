package com.barisyenigun.blogserver.request.post;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.User;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostLikeRequest {
    private User user;
    private Post post;
}
