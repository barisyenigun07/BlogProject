package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostLike;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostLikeService(PostLikeRepository postLikeRepository, PostService postService, UserService userService){
        this.postLikeRepository = postLikeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public void saveLike(Long postId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        Post post = postService.getPost(postId);
        PostLike postLike = new PostLike();
        postLike.setPost(post);
        postLike.setUser(user);
        postLikeRepository.save(postLike);
    }

    public void removeLike(Long id){
        postLikeRepository.deleteById(id);
    }
}
