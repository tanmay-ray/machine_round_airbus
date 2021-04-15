package com.challenge.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAuthResponseDTO {
    private String jwt;
}
