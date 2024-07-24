package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.PrincipalType;
import com.enigmacamp.maneyself.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrincipalTypeRepository extends JpaRepository<PrincipalType, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM t_principal_type WHERE principal=:principal"
    )
    Optional<PrincipalType> getPrincipalTypeByPrincipal(@Param("principal") String principal);
}
