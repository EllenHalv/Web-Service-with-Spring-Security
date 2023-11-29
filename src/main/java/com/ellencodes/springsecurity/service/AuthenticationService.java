package com.ellencodes.springsecurity.service;

import com.ellencodes.springsecurity.dao.request.SigninRequest;
import com.ellencodes.springsecurity.dao.request.SignupRequest;
import com.ellencodes.springsecurity.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignupRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
