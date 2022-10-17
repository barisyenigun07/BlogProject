package com.barisyenigun.blogserver.service.auth;

import com.barisyenigun.blogserver.entity.User;
import com.barisyenigun.blogserver.exception.PasswordAlreadyTakenException;
import com.barisyenigun.blogserver.exception.PasswordsMismatchException;
import com.barisyenigun.blogserver.exception.UserNotFoundException;
import com.barisyenigun.blogserver.exception.UsernameAlreadyTakenException;
import com.barisyenigun.blogserver.repository.UserRepository;
import com.barisyenigun.blogserver.request.auth.LoginRequest;
import com.barisyenigun.blogserver.request.auth.RegisterRequest;
import com.barisyenigun.blogserver.response.auth.AuthResponse;
import com.barisyenigun.blogserver.security.JwtUserDetailsService;
import com.barisyenigun.blogserver.util.TokenManager;
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

    private final TokenManager tokenManager;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public AuthService(JwtUserDetailsService userDetailsService,
                       TokenManager tokenManager,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(RegisterRequest body){
        Optional<User> existingUserByUsername = userRepository.findByUsername(body.getUsername());
        Optional<User> existingUserByPassword = userRepository.findByPassword(passwordEncoder.encode(body.getPassword()));
        if (existingUserByUsername.isPresent()){
            throw new UsernameAlreadyTakenException();
        }
        if (existingUserByPassword.isPresent()){
            throw new PasswordAlreadyTakenException();
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
            throw new UserNotFoundException();
        }

        if (!passwordEncoder.matches(body.getPassword(), optionalUser.get().getPassword())) {
            throw new PasswordsMismatchException();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }


}
