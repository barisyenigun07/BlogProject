package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long>{
}
