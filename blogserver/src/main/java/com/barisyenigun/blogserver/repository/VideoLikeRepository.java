package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.VideoLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLikeRepository extends JpaRepository<VideoLike,Long>{
}
