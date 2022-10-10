package com.barisyenigun.blogserver.controller.auth;

import com.barisyenigun.blogserver.request.auth.LoginRequest;
import com.barisyenigun.blogserver.request.auth.RegisterRequest;
import com.barisyenigun.blogserver.response.auth.AuthResponse;
import com.barisyenigun.blogserver.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest body){
        authService.register(body);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest body) {
        return authService.login(body);
    }
}
