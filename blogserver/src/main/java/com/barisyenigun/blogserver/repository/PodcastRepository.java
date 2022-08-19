package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast,Long>{
}
