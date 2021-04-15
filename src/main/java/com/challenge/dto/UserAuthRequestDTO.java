package com.challenge.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthRequestDTO {
    private String email;
    private String password;
}