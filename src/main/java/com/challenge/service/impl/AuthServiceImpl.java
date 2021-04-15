package com.challenge.service.impl;

import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.repository.UserAuthRepository;
import com.challenge.repository.RoleRepository;
import com.challenge.repository.entity.UserAuthEntity;
import com.challenge.service.UserAuthService;
import com.challenge.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements UserAuthService {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public Long registerUser(UserAuthRequestDTO newUser) throws Exception {
//        UserAuthEntity authEntity = AuthUtil.dtoToEntity(newUser);
//        authEntity.setRole(roleRepository.findByRoleKey("GENERAL").orElseThrow(Exception::new));
//        authEntity = userAuthRepository.save(authEntity);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAuthEntity userAuthEntity = userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email"));
        return new User(userAuthEntity.getEmail(), userAuthEntity.getPassword(), Collections.emptyList());
    }
}