package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.Following;
import com.barisyenigun.blogserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {
    Optional<Following> findByFollowerAndFollowed(User followed, User follower);
    List<Following> findAllByFollower(User follower);
}
