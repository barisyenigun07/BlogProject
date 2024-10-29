package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.UpdateUserRequest;
import com.barisyenigun.blogserver.response.UserResponse;
import com.barisyenigun.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping
    public void updateUser(@ModelAttribute UpdateUserRequest body){
        userService.updateUser(body);
    }

    @DeleteMapping
    public void deleteUser() {
        userService.deleteUser();
    }

}
