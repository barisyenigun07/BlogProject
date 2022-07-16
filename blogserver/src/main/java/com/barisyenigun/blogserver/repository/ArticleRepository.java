package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long>{
}
