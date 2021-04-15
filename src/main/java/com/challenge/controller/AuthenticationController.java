package com.challenge.controller;

import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.dto.UserAuthResponseDTO;
import com.challenge.service.UserAuthService;
import com.challenge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    UserAuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = "authenticate")
    public UserAuthResponseDTO createAuthenticationToken(@RequestBody UserAuthRequestDTO userAuthRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthRequestDTO.getEmail(), userAuthRequestDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        return UserAuthResponseDTO.builder().jwt(JwtUtil.generateToken(userAuthRequestDTO)).build();
    }

    @PostMapping("register")
    public Long registerUser(@RequestBody UserAuthRequestDTO newUser) throws Exception {
        return authService.registerUser(newUser);
    }
}
