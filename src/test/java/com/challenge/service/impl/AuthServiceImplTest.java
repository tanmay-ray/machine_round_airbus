package com.challenge.service.impl;

import com.challenge.repository.RoleRepository;
import com.challenge.repository.UserAuthRepository;
import com.challenge.repository.entity.UserAuthEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {

    @InjectMocks
    AuthServiceImpl authService;

    @Mock
    private UserAuthRepository userAuthRepository;

    @Mock
    private RoleRepository roleRepository;

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
}
