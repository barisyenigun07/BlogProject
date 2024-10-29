package com.barisyenigun.blogserver.controller;


import com.barisyenigun.blogserver.request.PostRequest;
import com.barisyenigun.blogserver.response.PostResponse;
import com.barisyenigun.blogserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public void createPost(@ModelAttribute PostRequest body){
        postService.createPost(body);
    }

    @PostMapping("/article/image/upload")
    public ResponseEntity<Map<String, Object>> uploadArticleImage(@RequestParam("file") MultipartFile file){
        return postService.uploadArticleImage(file);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @GetMapping
    public List<PostResponse> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/user/{userId}")
    public List<PostResponse> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    @GetMapping("/tag")
    public List<PostResponse> getPostsByTagName(@RequestParam("tag_name") String tagName) {
        return postService.getPostsByTagName(tagName);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @ModelAttribute PostRequest body){
        postService.updatePost(id, body);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}
