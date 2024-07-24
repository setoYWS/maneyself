package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.Role;
import com.enigmacamp.maneyself.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM t_role WHERE role=:role"
    )
    Optional<Role> getRoleByRole(@Param("role") String role);
}
