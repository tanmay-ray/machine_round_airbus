package com.challenge.controller;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserDTO;
import com.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("user/{userId}")
    public UserDTO getUser(@PathVariable Long userId) throws Exception {
        return userService.getUser(userId);
    }

    @GetMapping("users")
    public List<UserDTO> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("user")
    public Long createUser(@RequestBody NewUserDTO newUser) throws Exception {
        return userService.createUser(newUser);
    }

}
