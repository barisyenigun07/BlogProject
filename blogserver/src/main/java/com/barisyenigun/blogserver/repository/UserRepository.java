package com.barisyenigun.blogserver.repository;

import com.barisyenigun.blogserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}
