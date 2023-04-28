package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long> {
    @Query(value = "SELECT AVG(r.rankLevel) FROM Rank r JOIN r.post p WHERE p = :post")
    double findAverageRank(@Param("post") Post post);
}
