package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.CommentRequest;
import com.barisyenigun.blogserver.response.CommentResponse;
import com.barisyenigun.blogserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public void createComment(@RequestBody CommentRequest body, @RequestParam("post_id") Long postId){
        commentService.createComment(body, postId);
    }

    @PostMapping("/{commentId}/reply")
    public void replyComment(@PathVariable Long commentId, @RequestBody CommentRequest body) {
        commentService.replyComment(commentId, body);
    }

    @GetMapping("/{id}")
    public CommentResponse getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    @GetMapping
    public List<CommentResponse> getComments(@RequestParam("postId") Long postId){
        return commentService.getComments(postId);
    }

    @PutMapping("/{id}")
    public void updateComment(@RequestBody CommentRequest body, @PathVariable Long id){
        commentService.updateComment(body, id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }

}
