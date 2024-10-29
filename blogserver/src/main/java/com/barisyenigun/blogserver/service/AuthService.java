package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.*;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.ChangePasswordRequest;
import com.barisyenigun.blogserver.request.LoginRequest;
import com.barisyenigun.blogserver.request.RegisterRequest;
import com.barisyenigun.blogserver.response.AuthResponse;
import com.barisyenigun.blogserver.response.UserResponse;
import com.barisyenigun.blogserver.security.JwtUserDetailsService;
import com.barisyenigun.blogserver.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    private final JwtUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(JwtUserDetailsService userDetailsService,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(RegisterRequest body){
        Optional<User> existingUserByUsername = userRepository.findByUsername(body.getUsername());
        Optional<User> existingUserByEmail = userRepository.findByEmail(body.getEmail());
        if (existingUserByUsername.isPresent()){
            throw new AlreadyTakenException(AlreadyTakenType.USERNAME);
        }
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyTakenException(AlreadyTakenType.EMAIL);
        }
        if (!body.getPassword().equals(body.getPasswordRepeat())){
            throw new PasswordsMismatchException();
        }
        User user = new User();
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest body){
        Optional<User> optionalUser = userRepository.findByUsername(body.getUsername());
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException(ResourceType.USER);
        }
        if (!passwordEncoder.matches(body.getPassword(), optionalUser.get().getPassword())) {
            throw new PasswordsMismatchException();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());
        final String jwtToken = jwtUtil.generateJwtToken(userDetails);
        return AuthResponse.builder()
                .token(jwtToken)
                .user(UserResponse.fromEntity(optionalUser.get()))
                .build();
    }
    public void changePassword(Long userId, ChangePasswordRequest body){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER));
        if (body.getNewPassword().equals(body.getNewPasswordRepeat())){
            user.setPassword(passwordEncoder.encode(body.getNewPassword()));
        }
        userRepository.save(user);
    }
}
