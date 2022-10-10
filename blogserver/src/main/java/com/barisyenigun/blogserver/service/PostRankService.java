package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostRank;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.repository.PostRankRepository;
import com.barisyenigun.blogserver.request.post.PostRankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostRankService {
    private final PostRankRepository postRankRepository;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostRankService(PostRankRepository postRankRepository, PostService postService, UserService userService){
        this.postRankRepository = postRankRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public void createRank(Long postId, PostRankRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        Post post = postService.getPost(postId);
        PostRank postRank = new PostRank();
        postRank.setRankLevel(body.getRankLevel());
        postRank.setPost(post);
        post.setUser(user);
        postRankRepository.save(postRank);
    }

    public void removeRank(Long id){
        postRankRepository.deleteById(id);
    }

    public void updateRank(Long id){

    }
}
