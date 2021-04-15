package com.challenge.util;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserDTO;
import com.challenge.repository.entity.UserEntity;

public class UserUtil {
    public static UserDTO entityToDTO(UserEntity entity) {
        return UserDTO.builder()
                .userId(entity.getUserId())
                .name(entity.getName())
                .age(entity.getAge())
                .dob(entity.getDob())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .build();
    }

    public static UserEntity dtoToEntity(UserDTO dto) {
        return UserEntity.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .age(dto.getAge())
                .dob(dto.getDob())
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }
}
