package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByTagName(String tagName);
    boolean existsByTagName(String tagName);
}
