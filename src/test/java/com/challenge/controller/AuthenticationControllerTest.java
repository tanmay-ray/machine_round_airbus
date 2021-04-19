package com.challenge.controller;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.dto.UserAuthResponseDTO;
import com.challenge.service.UserAuthService;
import com.challenge.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {
    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    private UserAuthService userAuthService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    UserDetails userDetails = new User("someone@gmail.com", "qwerty",
            Collections.singletonList(new SimpleGrantedAuthority("GENERAL")));
    String jwt = JwtUtil.generateToken(userDetails);

    @Test
    public void isValidEmail_whenPresent() {
        String email = "someone@gmail.com";
        when(userAuthService.isValidEmail(anyString())).thenReturn(false);
        assertFalse(authenticationController.isValidEmail(email));
    }

    @Test
    public void isValidEmail_whenNotPresent() {
        String email = "someone@gmail.com";
        when(userAuthService.isValidEmail(anyString())).thenReturn(true);
        assertTrue(authenticationController.isValidEmail(email));
    }

    @Test
    public void createAuthenticationTokenTest() {
        when(userAuthService.loadUserByUsername(anyString())).thenReturn(userDetails);
        UserAuthResponseDTO respone = authenticationController.createAuthenticationToken(
                UserAuthRequestDTO.builder().email(userDetails.getUsername()).password(userDetails.getPassword()).build()
        );
        verify(authenticationManager).authenticate(any());
        assertEquals(jwt, respone.getJwt());
    }

    @Test(expected = RuntimeException.class)
    public void createAuthenticationTokenTest_whenAuthenticationFails() {
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Inavlid username or password"));
        authenticationController.createAuthenticationToken(
                UserAuthRequestDTO.builder().email(userDetails.getUsername()).password(userDetails.getPassword()).build()
        );
    }

    @Test
    public void registerUserTest() {
        NewUserDTO newUser = NewUserDTO.newUserDTOBuilder().build();
        when(userAuthService.registerUser(any(), anyString())).thenReturn(userDetails);
        UserAuthResponseDTO respone = authenticationController.registerUser(newUser);

        assertEquals(jwt, respone.getJwt());
    }

}
