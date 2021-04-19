package com.challenge.service.impl;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserDTO;
import com.challenge.repository.UserRepository;
import com.challenge.repository.entity.UserEntity;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getUserTest() {
        UserEntity entity = UserEntity.builder().name("John Doe").email("john.doe@gmail.com").build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(entity));
        UserDTO user = userService.getUser("john.doe@gmail.com");
        assertEquals("John Doe", user.getName());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getUserTest_userNotFound() {
        when(userRepository.findByEmail(anyString())).thenThrow(new UsernameNotFoundException("Username not found"));
        UserDTO user = userService.getUser("john.doe@gmail.com");
    }

    @Test
    public void getUserListTest() {
        List<UserEntity> entities = new ArrayList<>();
        entities.add(UserEntity.builder().name("John Doe").email("john.doe@gmail.com").build());
        entities.add(UserEntity.builder().name("Jane Doe").email("jane.doe@gmail.com").build());
        when(userRepository.findAll()).thenReturn(entities);
        List<UserDTO> users = userService.getUserList();

        assertEquals(entities.size(), users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Doe", users.get(1).getName());
    }

    @Test
    public void createUserTest() {
        Date dob = Date.from(LocalDate.now().minusYears(20).atStartOfDay(ZoneId.systemDefault()).toInstant());
        NewUserDTO user = NewUserDTO.newUserDTOBuilder()
                .email("john.doe@gmail.com").name("John Doe")
                .gender("Male").password("qwerty").dob(dob)
                .build();
        ArgumentCaptor<UserEntity> argCaptor = ArgumentCaptor.forClass(UserEntity.class);
        userService.createUser(user);

        verify(userRepository).save(argCaptor.capture());
        assertEquals("john.doe@gmail.com", argCaptor.getValue().getEmail());
        assertEquals(Integer.valueOf(20), argCaptor.getValue().getAge());
    }

}
