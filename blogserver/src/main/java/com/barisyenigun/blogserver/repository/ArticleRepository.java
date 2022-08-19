package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
}
