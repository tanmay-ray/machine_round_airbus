package com.challenge.service.impl;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.exception.RoleNotFoundException;
import com.challenge.repository.UserAuthRepository;
import com.challenge.repository.RoleRepository;
import com.challenge.repository.entity.UserAuthEntity;
import com.challenge.service.UserAuthService;
import com.challenge.service.UserService;
import com.challenge.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class AuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Boolean isValidEmail(String email) {
        return !userAuthRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public UserDetails registerUser(NewUserDTO newUser, String role) {
        UserAuthEntity userAuthEntity = AuthUtil.dtoToEntity(UserAuthRequestDTO.builder()
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .build());
        userAuthEntity.setRole(roleRepository.findByRoleKey(role)
                .orElseThrow(() -> new RoleNotFoundException(role)));

        userAuthEntity = userAuthRepository.save(userAuthEntity);
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(userAuthEntity.getRole().getRoleKey())
        );

        userService.createUser(newUser);
        return new User(userAuthEntity.getEmail(), userAuthEntity.getPassword(), authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserAuthEntity userAuthEntity = userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email"));
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(userAuthEntity.getRole().getRoleKey())
        );

        return new User(userAuthEntity.getEmail(), userAuthEntity.getPassword(), authorities);
    }
}
