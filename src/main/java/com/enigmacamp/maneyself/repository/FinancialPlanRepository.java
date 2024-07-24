package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.FinancialPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialPlanRepository extends JpaRepository<FinancialPlan, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM t_financial_plan WHERE user_id=:userId"
    )
    FinancialPlan findFinancialPlanByUserId(@Param("userId") String userId);
}
