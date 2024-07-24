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
@Table(name = "t_allocation")
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "financial_plan_id")
    private FinancialPlan financialPlan;
    private Long needs;
    private Long wish;
    private Long saving;
    private Long payment;
    private Long emergency;
    private Long invest;
    private Long donate;
    @Column(name = "transaction_date")
    private Date transactionDate;
}