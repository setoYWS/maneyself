package com.enigmacamp.maneyself.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_management_plan")
public class ManagementType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private ManagementPlanType type;
    @OneToMany
    private List<PrincipalType> principals;

    @Getter
    public enum ManagementPlanType{
        A("50/30/20"), B("40/10/10/30/10");
        private final String type;

        ManagementPlanType(String type){
            this.type = type;
        }
    }
}
