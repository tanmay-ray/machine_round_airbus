package com.challenge.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_DETAIL")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private Integer age;

    private String gender;

    @Column(unique = true)
    private String email;

    private Long phone;
}
