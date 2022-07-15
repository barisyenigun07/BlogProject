package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException());
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
