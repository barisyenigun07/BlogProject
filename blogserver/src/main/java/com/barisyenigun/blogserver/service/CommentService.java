package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.Comment;
import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.ResourceNotFoundException;
import com.barisyenigun.blogserver.exception.ResourceType;
import com.barisyenigun.blogserver.exception.UnauthorizedException;
import com.barisyenigun.blogserver.repository.CommentRepository;
import com.barisyenigun.blogserver.repository.PostRepository;
import com.barisyenigun.blogserver.request.CommentRequest;
import com.barisyenigun.blogserver.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public void createComment(CommentRequest body, Long postId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        Comment comment = new Comment();
        comment.setComment(body.getComment());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public CommentResponse getComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMMENT));
        return CommentResponse.fromEntity(comment);
    }

    public List<CommentResponse> getComments(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.POST));
        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments.stream().map(comment -> CommentResponse.fromEntity(comment)).collect(Collectors.toList());
    }

    public void updateComment(CommentRequest body, Long id){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMMENT));
        if (!comment.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        comment.setComment(body.getComment());
        commentRepository.save(comment);
    }

    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMMENT));
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        if (!comment.getUser().equals(user)){
            throw new UnauthorizedException();
        }
        commentRepository.delete(comment);
    }
}

