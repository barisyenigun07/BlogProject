package com.barisyenigun.blogserver.controller;


import com.barisyenigun.blogserver.request.PostRequest;
import com.barisyenigun.blogserver.response.PostResponse;
import com.barisyenigun.blogserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping
    public void createPost(@ModelAttribute PostRequest body){
        postService.createPost(body);
    }

    /*@PostMapping("/article/image/upload")
    public void uploadArticleImage(@RequestParam("file") MultipartFile file){
        postService.uploadArticleImage(file);
    }*/

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @GetMapping
    public List<PostResponse> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{id}/caption-photo/download")
    public byte[] getCaptionPhoto(@PathVariable Long id){
        return postService.getCaptionPhoto(id);
    }

    @GetMapping("/{id}/content/download")
    public byte[] getFileContent(@PathVariable Long id){
        return postService.getFileContent(id);
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
