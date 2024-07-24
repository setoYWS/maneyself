package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.entity.AppUser;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential Use!"));
        return AppUser.builder()
                .id((user.getId()))
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getRole())
                .build();
    }

    @Override
    public AppUser loadUserById(String id) throws UsernameNotFoundException {
        User user = userRepository.getUserById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential Use!"));
        return AppUser.builder()
                .id((user.getId()))
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getRole())
                .build();
    }
}
