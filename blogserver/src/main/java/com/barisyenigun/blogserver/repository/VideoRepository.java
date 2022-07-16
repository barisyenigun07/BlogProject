package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long>{
}
