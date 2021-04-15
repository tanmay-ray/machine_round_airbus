package com.challenge.service;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUser(Long userId) throws Exception;
    List<UserDTO> getUserList();
    void createUser(NewUserDTO newUser);
}
