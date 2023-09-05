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

    @GetMapping("/{id}/profile-photo/download")
    public byte[] getProfilePhoto(@PathVariable Long id) {
        return userService.getProfilePhoto(id);
    }

    @GetMapping("/{id}/caption-photo/download")
    public byte[] getCaptionPhoto(@PathVariable Long id) {
        return userService.getCaptionPhoto(id);
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
