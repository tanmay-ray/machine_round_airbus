package com.challenge.service.impl;

import com.challenge.dto.NewUserDTO;
import com.challenge.exception.RoleNotFoundException;
import com.challenge.repository.RoleRepository;
import com.challenge.repository.UserAuthRepository;
import com.challenge.repository.entity.RoleEntity;
import com.challenge.repository.entity.UserAuthEntity;
import com.challenge.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {

    @InjectMocks
    AuthServiceImpl authService;

    @Mock
    private UserAuthRepository userAuthRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserService userService;

    @Test
    public void isValidEmailTest_whenPresent() {
        when(userAuthRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(UserAuthEntity.builder().build()));
        Boolean isValid = authService.isValidEmail("someone@gmail.com");
        assertFalse(isValid);
    }

    @Test
    public void isValidEmailTest_whenNotPresent() {
        when(userAuthRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Boolean isValid = authService.isValidEmail("someone@gmail.com");
        assertTrue(isValid);
    }

    @Test(expected = RoleNotFoundException.class)
    public void registerUserTest_roleNotFound() {
        when(roleRepository.findByRoleKey(anyString())).thenReturn(Optional.empty());
        authService.registerUser(NewUserDTO.newUserDTOBuilder()
                .email("someone@gmail.com").password("qwerty").build(), "INVALID_ROLE");
    }

    @Test
    public void registerUserTest() {
        NewUserDTO newUser = NewUserDTO.newUserDTOBuilder().email("someone@gmail.com").password("qwerty").build();
        RoleEntity roleEntity = RoleEntity.builder().roleId(2L).roleKey("GENERAL").build();
        UserAuthEntity entity = UserAuthEntity.builder()
                .email("someone@gmail.com").password("qwerty")
                .role(roleEntity).userAuthId(1L).build();

        when(roleRepository.findByRoleKey(anyString()))
                .thenReturn(Optional.of(roleEntity));
        when(userAuthRepository.save(any())).thenReturn(entity);

        UserDetails details = authService.registerUser(newUser, "GENERAL");

        verify(userService).createUser(newUser);
        assertEquals("someone@gmail.com", details.getUsername());
        assertEquals("qwerty", details.getPassword());
        assertEquals(1, details.getAuthorities().size());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameTest_userNotFound() {
        when(userAuthRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        authService.loadUserByUsername("someone@gmail.com");
    }

    @Test
    public void loadUserByUsernameTest() {
        RoleEntity roleEntity = RoleEntity.builder().roleId(2L).roleKey("GENERAL").build();
        UserAuthEntity entity = UserAuthEntity.builder()
                .email("someone@gmail.com").password("qwerty")
                .role(roleEntity).userAuthId(1L).build();

        when(userAuthRepository.findByEmail(anyString())).thenReturn(Optional.of(entity));
        UserDetails details = authService.loadUserByUsername("someone@gmail.com");

        assertEquals("someone@gmail.com", details.getUsername());
        assertEquals("qwerty", details.getPassword());
        assertEquals(1, details.getAuthorities().size());
    }
}
