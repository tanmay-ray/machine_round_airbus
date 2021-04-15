package com.challenge.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NewUserDTO extends UserDTO {
    private String password;

    @Builder(builderMethodName = "newUserDTOBuilder")
    public NewUserDTO(
            Long userId,
            String name,
            Date dob,
            Integer age,
            String gender,
            String email,
            Long phone,
            String password
    ) {
        super(
                userId,
                name,
                dob,
                age,
                gender,
                email,
                phone
        );
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
