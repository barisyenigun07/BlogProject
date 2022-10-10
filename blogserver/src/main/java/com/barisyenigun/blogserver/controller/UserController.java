package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/upload/profile_photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadProfilePhoto(@RequestParam("file") MultipartFile file){
        userService.uploadProfilePhoto(file);
    }


}
