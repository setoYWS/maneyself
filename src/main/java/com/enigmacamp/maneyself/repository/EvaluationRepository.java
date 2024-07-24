package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, String> {
}
