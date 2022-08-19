package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCommentRepository extends JpaRepository<VideoComment,Long>{
}
