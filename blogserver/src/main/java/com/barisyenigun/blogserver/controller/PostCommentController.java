package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.post.PostCommentRequest;
import com.barisyenigun.blogserver.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/comment")
public class PostCommentController {
    private final PostCommentService postCommentService;

    @Autowired
    public PostCommentController(PostCommentService postCommentService){
        this.postCommentService = postCommentService;
    }

    public void postCommment(PostCommentRequest body){

    }
}
