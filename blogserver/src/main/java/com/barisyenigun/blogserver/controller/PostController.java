package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.ArticleRequest;
import com.barisyenigun.blogserver.request.PodcastRequest;
import com.barisyenigun.blogserver.request.VideoRequest;
import com.barisyenigun.blogserver.response.PostResponse;
import com.barisyenigun.blogserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/article")
    public void createArticle(@ModelAttribute ArticleRequest body){
        postService.createArticle(body);
    }

    /*@PostMapping("/article/image/upload")
    public void uploadArticleImage(@RequestParam("file") MultipartFile file){
        postService.uploadArticleImage(file);
    }*/

    @PostMapping( "/video")
    public void createVideo(@ModelAttribute VideoRequest body){
        postService.createVideo(body);
    }

    @PostMapping("/podcast")
    public void createPodcast(@ModelAttribute PodcastRequest body){
        postService.createPodcast(body);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @GetMapping
    public List<PostResponse> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{id}/caption_photo/download")
    public byte[] getCaptionPhoto(@PathVariable Long id){
        return postService.getCaptionPhoto(id);
    }

    @GetMapping("/{id}/content/download")
    public byte[] getFileContent(@PathVariable Long id){
        return postService.getFileContent(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}
