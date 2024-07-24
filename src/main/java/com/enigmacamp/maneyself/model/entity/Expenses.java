package com.enigmacamp.maneyself.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private PrincipalType.Principal principal;
    private String title;
    private Long nominal;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
