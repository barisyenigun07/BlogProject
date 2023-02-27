package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.ArticleRequest;
import com.barisyenigun.blogserver.request.PodcastRequest;
import com.barisyenigun.blogserver.request.VideoRequest;
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
    public void createArticle(@RequestBody ArticleRequest body){
        postService.createArticle(body);
    }

    @PostMapping( "/video")
    public void createVideo(@RequestBody VideoRequest body){
        postService.createVideo(body);
    }

    @PostMapping("/podcast")
    public void createPodcast(@RequestBody PodcastRequest body){
        postService.createPodcast(body);
    }
}
