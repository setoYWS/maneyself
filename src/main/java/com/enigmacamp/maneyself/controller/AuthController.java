package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.LoginRequest;
import com.enigmacamp.maneyself.model.dto.request.RegisterRequest;
import com.enigmacamp.maneyself.model.dto.response.AuthResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<AuthResponse>> signUp(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.signUp(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateAuthResponse(
                                "Register User Success",
                                Optional.of(response)
                        )
                );
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse<AuthResponse>> signIn(@RequestBody LoginRequest request) {
        AuthResponse response = authService.signIn(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateAuthResponse(
                                "Login User Success",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<AuthResponse> generateAuthResponse(String message, Optional<AuthResponse> response) {
        return CommonResponse.<AuthResponse>builder()
                .message(message)
                .data(response).build();
    }
}
