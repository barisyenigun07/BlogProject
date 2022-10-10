package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends PagingAndSortingRepository<PostComment,Long> {
    Page<PostComment> findAllByPost(Post post, Pageable pageable);
}
