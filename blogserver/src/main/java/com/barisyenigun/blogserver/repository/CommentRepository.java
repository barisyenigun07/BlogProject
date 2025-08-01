package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
   List<Comment> findAllByPost(Post post);
}
