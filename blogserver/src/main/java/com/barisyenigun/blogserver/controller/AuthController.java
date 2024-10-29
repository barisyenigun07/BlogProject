package com.barisyenigun.blogserver.controller;

import com.barisyenigun.blogserver.request.ChangePasswordRequest;
import com.barisyenigun.blogserver.request.LoginRequest;
import com.barisyenigun.blogserver.request.RegisterRequest;
import com.barisyenigun.blogserver.response.AuthResponse;
import com.barisyenigun.blogserver.service.AuthService;
import com.barisyenigun.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService){
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest body){
        authService.register(body);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest body) {
        return authService.login(body);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest body){
        authService.changePassword(userService.getAuthenticatedUserId(), body);
    }
}
