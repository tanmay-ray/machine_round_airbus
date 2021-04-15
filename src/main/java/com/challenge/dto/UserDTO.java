package com.challenge.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String name;
    private Date dob;
    private Integer age;
    private String gender;
    private String email;
    private Long phone;
}
