package com.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    @JsonIgnore
    private Long userId;
    private String name;
    private Date dob;
    private Integer age;
    private String gender;
    private String email;
    private Long phone;
}
