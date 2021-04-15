package com.challenge.service.impl;

import com.challenge.dto.NewUserDTO;
import com.challenge.dto.UserAuthRequestDTO;
import com.challenge.dto.UserDTO;
import com.challenge.repository.UserRepository;
import com.challenge.repository.entity.UserEntity;
import com.challenge.service.UserAuthService;
import com.challenge.service.UserService;
import com.challenge.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthService authService;

    private Integer calcAge(Date dob) {
        return LocalDate.now().getYear() - dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }

    @Override
    public UserDTO getUser(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(Exception::new);
        return UserUtil.entityToDTO(userEntity);
    }

    @Override
    public List<UserDTO> getUserList() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserUtil::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long createUser(NewUserDTO newUser) throws Exception {
        newUser.setAge(calcAge(newUser.getDob()));
        Long userId = userRepository.save(UserUtil.dtoToEntity(newUser)).getUserId();

        UserAuthRequestDTO userAuthDTO = UserAuthRequestDTO.builder()
                .userId(userId)
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .build();

        return authService.registerUser(userAuthDTO);
    }
}