package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpensesRepository extends JpaRepository<Expenses, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT sum(nominal) FROM t_expenses WHERE EXTRACT(MONTH FROM transaction_date) =:month AND EXTRACT(YEAR FROM transaction_date) =:year"
    )
    Long getTotalExpense(@Param("month") Integer month, @Param("year") Integer year);
}
