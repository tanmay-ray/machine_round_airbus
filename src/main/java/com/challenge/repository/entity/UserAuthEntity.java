package com.challenge.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_AUTH")
public class UserAuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthId;
    private String email;
    private String password;

    @ManyToOne()
    @JoinColumn(name = "ROLE_ID")
    private RoleEntity role;

}
