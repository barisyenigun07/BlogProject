package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
}
