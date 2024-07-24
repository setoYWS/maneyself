package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM t_allocation WHERE financial_plan_id=:financialPlanId"
    )
    Allocation findAllocationByFinancialPlan(@Param("financialPlanId") String financialPlan);
}
