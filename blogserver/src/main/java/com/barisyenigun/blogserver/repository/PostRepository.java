package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;


import com.barisyenigun.blogserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByUserAndPostType(User user, String postType, Pageable pageable);
}
