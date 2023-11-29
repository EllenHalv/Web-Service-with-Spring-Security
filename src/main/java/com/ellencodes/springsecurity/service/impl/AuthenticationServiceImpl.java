package com.ellencodes.springsecurity.service.impl;

import com.ellencodes.springsecurity.dao.request.SigninRequest;
import com.ellencodes.springsecurity.dao.request.SignupRequest;
import com.ellencodes.springsecurity.dao.response.JwtAuthenticationResponse;
import com.ellencodes.springsecurity.entity.Role;
import com.ellencodes.springsecurity.entity.User;
import com.ellencodes.springsecurity.repository.UserRepository;
import com.ellencodes.springsecurity.service.AuthenticationService;
import com.ellencodes.springsecurity.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class handling user authentication-related operations.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationManager authenticationManager;

    // Registers a new user based on the provided signup request.
    @Override
    public JwtAuthenticationResponse signup(SignupRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        var message = jwtServiceImpl.getResponseMessage("You are signed up!");
        return JwtAuthenticationResponse.builder().responseMessage(message).token(jwt).build();
    }

    // Authenticates a user based on the provided signin request.
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        var message = jwtServiceImpl.getResponseMessage("You are signed in! Save the token given below or you will regret it. You will need it for accessing protected resources.");
        return JwtAuthenticationResponse.builder().responseMessage(message).token(jwt).build();
    }
}
