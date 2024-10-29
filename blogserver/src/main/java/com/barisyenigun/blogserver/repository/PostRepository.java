package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;


import com.barisyenigun.blogserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUserAndPostType(User user, String postType);
    List<Post> findAllByUser(User user);
}
