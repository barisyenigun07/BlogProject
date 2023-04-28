package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.UpdateUserRequest;
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

    @PutMapping
    public void updateUser(@ModelAttribute UpdateUserRequest body){
        userService.updateUser(body);
    }

}
