package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    AppUser loadUserById(String id) throws UsernameNotFoundException;
}
