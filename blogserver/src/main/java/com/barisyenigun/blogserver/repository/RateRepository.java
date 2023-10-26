package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Post;
import com.barisyenigun.blogserver.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
    @Query(value = "SELECT COALESCE(AVG(CASE WHEN r.rateLevel IS NULL THEN 0.0 ELSE r.rateLevel END), 0.0) FROM Rate r JOIN r.post p WHERE p = :post")
    double findAverageRate(@Param("post") Post post);
}
