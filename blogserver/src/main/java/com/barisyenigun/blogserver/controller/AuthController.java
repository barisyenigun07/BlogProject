package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.LoginRequest;
import com.barisyenigun.blogserver.request.RegisterRequest;
import com.barisyenigun.blogserver.response.AuthResponse;
import com.barisyenigun.blogserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest body){
        authService.register(body);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest body) {
        return authService.login(body);
    }
}
