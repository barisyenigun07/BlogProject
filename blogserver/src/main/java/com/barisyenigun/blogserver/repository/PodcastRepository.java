package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastRepository extends JpaRepository<Podcast,Long>{
}
