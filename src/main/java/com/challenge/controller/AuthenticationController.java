package com.challenge.controller;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.dto.UserAuthResponseDTO;
import com.challenge.service.UserAuthService;
import com.challenge.service.UserService;
import com.challenge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = "authenticate")
    public UserAuthResponseDTO createAuthenticationToken(@RequestBody UserAuthRequestDTO userAuthRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthRequestDTO.getEmail(), userAuthRequestDTO.getPassword())
        );

        final UserDetails userDetails = userAuthService
                .loadUserByUsername(userAuthRequestDTO.getEmail());

        return UserAuthResponseDTO.builder().jwt(JwtUtil.generateToken(userDetails)).build();
    }

    @PostMapping("register")
    public UserAuthResponseDTO registerUser(@RequestBody NewUserDTO newUser) throws Exception {

        userService.createUser(newUser);
        final String pwd = passwordEncoder.encode(newUser.getPassword());
        return userAuthService.registerUser(
                UserAuthRequestDTO.builder()
                        .email(newUser.getEmail())
                        .password(pwd)
                        .build()
        );
    }
}
