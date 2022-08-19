package com.barisyenigun.blogserver.service;

import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.PasswordsMismatchException;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.exception.UsernameAlreadyTakenException;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.LoginRequest;
import com.barisyenigun.blogserver.request.RegisterRequest;
import com.barisyenigun.blogserver.response.AuthResponse;
import com.barisyenigun.blogserver.security.JwtUserDetailsService;
import com.barisyenigun.blogserver.util.TokenManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void register(RegisterRequest body){
        Optional<User> existingUser = userRepository.findByUsername(body.getUsername());
        if (existingUser.isPresent()){
            throw new UsernameAlreadyTakenException();
        }
        User user = new User();
        user.setName(body.getName());
        user.setProfilePhotoLink(body.getProfilePhotoLink());
        user.setEmail(body.getEmail());
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest body){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(),body.getPassword()));
        }
        catch (UserNotFoundException | BadCredentialsException | PasswordsMismatchException e){
            e.printStackTrace();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
