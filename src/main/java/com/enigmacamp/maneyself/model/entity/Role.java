package com.enigmacamp.maneyself.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole role;

    public enum ERole {
        ROLE_USER,
        ROLE_ADMIN
    }
}
