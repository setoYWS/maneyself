package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.ManagementType;
import com.enigmacamp.maneyself.model.entity.PrincipalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementTypeRepository extends JpaRepository<ManagementType, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM t_management_plan WHERE type=:type"
    )
    Optional<ManagementType> getManagementTypeByType(@Param("type") String type);
}
