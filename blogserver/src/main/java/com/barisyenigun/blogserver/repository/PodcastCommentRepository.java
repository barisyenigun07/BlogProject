package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.PodcastComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastCommentRepository extends JpaRepository<PodcastComment,Long>{
}
