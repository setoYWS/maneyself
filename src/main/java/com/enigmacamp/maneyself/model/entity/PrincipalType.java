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
@Table(name = "t_principal_type")
public class PrincipalType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private Principal principal;

    public enum Principal {
        NEEDS,
        WISH,
        SAVING,
        PAYMENT,
        EMERGENCY,
        INVEST,
        DONATE
    }
}
