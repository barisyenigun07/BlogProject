package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLike,Long>{
}
