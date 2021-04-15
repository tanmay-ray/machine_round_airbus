package com.challenge.util;

import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.repository.entity.UserAuthEntity;

public class AuthUtil {
    private AuthUtil() {}

    public static UserAuthEntity dtoToEntity(UserAuthRequestDTO user) {
        return UserAuthEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static UserAuthRequestDTO entityToDTO(UserAuthEntity user) {
        return UserAuthRequestDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
