package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.PostRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRankRepository extends JpaRepository<PostRank,Long> {
    @Query(value = "SELECT AVG(pr.rankLevel) FROM PostRank pr, Post p WHERE pr.post = p")
    double findAverageRank(Post post);
}
