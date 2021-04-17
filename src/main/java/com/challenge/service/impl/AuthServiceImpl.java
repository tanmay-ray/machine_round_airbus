package com.challenge.service.impl;

import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.dto.UserAuthResponseDTO;
import com.challenge.repository.UserAuthRepository;
import com.challenge.repository.RoleRepository;
import com.challenge.repository.entity.UserAuthEntity;
import com.challenge.service.UserAuthService;
import com.challenge.util.AuthUtil;
import com.challenge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthServiceImpl implements UserAuthService {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public Boolean isValidEmail(String email) {
        return !userAuthRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserAuthResponseDTO registerUser(UserAuthRequestDTO newUser) throws Exception {
        UserAuthEntity authEntity = AuthUtil.dtoToEntity(newUser);
        authEntity.setRole(roleRepository.findByRoleKey("GENERAL").orElseThrow(Exception::new));
        authEntity = userAuthRepository.save(authEntity);
        UserDetails userDetails = loadUserByUsername(authEntity.getEmail());
        return UserAuthResponseDTO.builder().jwt(JwtUtil.generateToken(userDetails)).build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAuthEntity userAuthEntity = userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email"));
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(userAuthEntity.getRole().getRoleKey())
        );

        return new User(userAuthEntity.getEmail(), userAuthEntity.getPassword(), authorities);
    }
}
