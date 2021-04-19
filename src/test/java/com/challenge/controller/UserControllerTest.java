package com.challenge.controller;

import com.challenge.dto.UserDTO;
import com.challenge.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    private UserService userService;

    @Test(expected = UsernameNotFoundException.class)
    public void getUserTest_whenNotPresent() {
        String email = "someone@gmail.com";
        when(userService.getUser(anyString())).thenThrow(new UsernameNotFoundException("No user exists with email: " + email));
        userController.getUser(email);
    }

    @Test
    public void getUserTest() {
        String email = "someone@gmail.com";
        UserDTO userResponse = UserDTO.builder().name("John Doe").build();
        when(userService.getUser(anyString())).thenReturn(userResponse);
        UserDTO user = userController.getUser(email);
        assertEquals(userResponse, user);
    }

    @Test
    public void getUserListTest() {
        String email = "someone@gmail.com";
        List<UserDTO> usersResponse = Collections.singletonList(UserDTO.builder().name("John Doe").build());
        when(userService.getUserList()).thenReturn(usersResponse);
        List<UserDTO> users = userController.getUserList();
        assertEquals(usersResponse, users);
    }

}
