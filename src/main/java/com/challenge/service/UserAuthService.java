package com.challenge.service;

import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.repository.entity.UserAuthEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService extends UserDetailsService {
    Long registerUser(UserAuthRequestDTO user) throws Exception;
}
