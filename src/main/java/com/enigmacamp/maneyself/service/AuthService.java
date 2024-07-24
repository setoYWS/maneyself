package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.LoginRequest;
import com.enigmacamp.maneyself.model.dto.request.RegisterRequest;
import com.enigmacamp.maneyself.model.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse signUp(RegisterRequest request);
    AuthResponse signIn(LoginRequest request);
}
