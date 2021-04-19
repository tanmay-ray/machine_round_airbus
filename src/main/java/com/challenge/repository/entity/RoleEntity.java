package com.challenge.repository.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "ROLE")
public class RoleEntity {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true)
    private String roleKey;
    private String roleDescription;
}
