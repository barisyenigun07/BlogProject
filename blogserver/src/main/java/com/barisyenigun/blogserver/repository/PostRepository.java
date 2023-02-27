package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;


import com.barisyenigun.blogserver.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByTitle(String title, Pageable pageable);
    Page<Post> findAllByTag(Tag tag, Pageable pageable);
}
