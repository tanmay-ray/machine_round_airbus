package com.challenge.service;

import com.challenge.dto.NewUserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService extends UserDetailsService {
    Boolean isValidEmail(String email);
    UserDetails registerUser(NewUserDTO newUser, String role);
}
