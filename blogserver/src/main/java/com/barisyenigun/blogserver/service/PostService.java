package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostType;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.request.post.PostRequest;
import com.barisyenigun.blogserver.service.filestore.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

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

    public void postArticle(PostRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setContent(body.getContent());
        post.setType(String.valueOf(PostType.ARTICLE));
        post.setPublishedTime(LocalDate.now());
        post.setModifiedTime(LocalDate.now());
        post.setTag(body.getTag());
        post.setUser(user);
        postRepository.save(post);
    }

    public void postVideo(MultipartFile file, PostRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        Post post = new Post();
        post.setTitle(body.getTitle());
        if (!file.isEmpty()){

        }
    }

    public void postPodcast(MultipartFile file, PostRequest body){

    }

    public Post getPost(Long id){
        return postRepository.findById(id).orElseThrow();
    }
}
