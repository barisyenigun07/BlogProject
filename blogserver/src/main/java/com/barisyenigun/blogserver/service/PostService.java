package com.barisyenigun.blogserver.service;


import com.barisyenigun.blogserver.bucket.BucketName;
import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.UnauthorizedException;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.request.ArticleRequest;
import com.barisyenigun.blogserver.request.PodcastRequest;
import com.barisyenigun.blogserver.request.VideoRequest;
import com.barisyenigun.blogserver.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final StorageService storageService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, StorageService storageService){
        this.postRepository = postRepository;
        this.userService = userService;
        this.storageService = storageService;
    }

    public void createArticle(ArticleRequest body){

        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setContent(body.getContent());
        post.setTag(body.getTag());
        post.setDescription(body.getDescription());
        post.setPostType(String.valueOf(body.getPostType()));
        post.setUser(user);
        postRepository.save(post);
    }

    public void createVideo(VideoRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setPostType(String.valueOf(body.getPostType()));
        post.setTag(body.getTag());
        post.setUser(user);

        if (!body.getFile().isEmpty()){
            Map<String, String> metadata = extractMetadata(body.getFile());
            String path = String.format("%s/videos", BucketName.STORAGE_BUCKET.getBucketName());
            String filename = String.format("%s-%s", UUID.randomUUID(), body.getFile().getOriginalFilename());
            try {
                storageService.upload(path, filename, Optional.of(metadata), body.getFile().getInputStream());
                post.setContent(filename);
            }
            catch (IOException e){

            }
        }

        postRepository.save(post);

    }

    public void createPodcast(PodcastRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setTag(body.getTag());
        post.setDescription(body.getDescription());
        post.setPostType(String.valueOf(body.getPostType()));
        post.setUser(user);

        if (!body.getFile().isEmpty()){
            Map<String, String> metadata = extractMetadata(body.getFile());
            String path = String.format("%s/podcasts",BucketName.STORAGE_BUCKET.getBucketName());
            String filename = String.format("%s-%s",UUID.randomUUID(), body.getFile().getOriginalFilename());
            try {
                storageService.upload(path, filename, Optional.of(metadata), body.getFile().getInputStream());
                post.setContent(filename);
            }
            catch (IOException e){

            }
        }
        postRepository.save(post);
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        return PostResponse.fromEntity(post);
    }

    public List<PostResponse> getPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> PostResponse.fromEntity(post)).collect(Collectors.toList());
    }

    public void deletePost(Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        if (!post.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        postRepository.deleteById(id);
    }

    private Map<String, String> extractMetadata(MultipartFile file){
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isVideo(MultipartFile file){

    }
    private void isPodcast(MultipartFile file){

    }

    private void isImage(MultipartFile file){

    }
}
