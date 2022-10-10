package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByPassword(String password);

    Page<User> findAllByName(String name, Pageable pageable);
}
