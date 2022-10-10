package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.post.PostRequest;
import com.barisyenigun.blogserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/article")
    public void postArticle(@RequestBody PostRequest body){
        postService.postArticle(body);
    }
}
