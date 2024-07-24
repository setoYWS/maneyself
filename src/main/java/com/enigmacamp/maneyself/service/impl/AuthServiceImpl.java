package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.LoginRequest;
import com.enigmacamp.maneyself.model.dto.request.RegisterRequest;
import com.enigmacamp.maneyself.model.dto.response.AuthResponse;
import com.enigmacamp.maneyself.model.entity.AppUser;
import com.enigmacamp.maneyself.model.entity.Role;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.RoleRepository;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.security.JwtUtil;
import com.enigmacamp.maneyself.service.AuthService;
import com.enigmacamp.maneyself.service.RoleService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse signUp(RegisterRequest request) {
        Optional<User> foundUser = userRepository.getUserByEmail(request.getEmail());
        if(foundUser.isPresent()){
            throw new ResourceNotFoundException("Email already registered!");
        }
        Role role = roleService.getOrSave(Role.ERole.valueOf(request.getRole()));
        User user = User.builder()
                .role(role)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        user = userRepository.saveAndFlush(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().getRole())
                .build();
    }

    @Override
    public AuthResponse signIn(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();

        String token = jwtUtil.generateToken(appUser);

        return AuthResponse.builder()
                .token(token)
                .role(appUser.getRole())
                .email(appUser.getEmail())
                .build();
    }
}
