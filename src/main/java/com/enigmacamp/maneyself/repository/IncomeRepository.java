package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT sum(nominal) FROM t_income WHERE EXTRACT(MONTH FROM transaction_date) =:month AND EXTRACT(YEAR FROM transaction_date) =:year"
    )
    Long getTotalIncome(@Param("month") Integer month, @Param("year") Integer year);
}
