package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostComment;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.repository.PostCommentRepository;
import com.barisyenigun.blogserver.request.post.PostCommentRequest;
import com.barisyenigun.blogserver.response.post.PostCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository, UserService userService, PostService postService){
        this.postCommentRepository = postCommentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public void postComment(PostCommentRequest body, Long postId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        Post post = postService.getPost(postId);
        PostComment postComment = new PostComment();
        postComment.setComment(body.getComment());
        postComment.setPost(post);
        postComment.setUser(user);
        postCommentRepository.save(postComment);
    }

    public void deleteComment(Long id){
        PostComment postComment = postCommentRepository.findById(id).orElseThrow(() -> new IllegalStateException());
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new UserNotFoundException());
        if (!user.equals(postComment.getUser())){
            throw new IllegalStateException();
        }
        postCommentRepository.delete(postComment);
    }

    public void updateComment(Long id){

    }

    public PostCommentResponse getComment(Long id){
        return null;
    }

    public Page<PostCommentResponse> getComments(Long postId){
        return null;
    }
}
