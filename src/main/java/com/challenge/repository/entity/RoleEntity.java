package com.challenge.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class RoleEntity {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleKey;
    private String roleDescription;
}
