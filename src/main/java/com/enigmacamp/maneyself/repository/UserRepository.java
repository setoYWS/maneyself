package com.enigmacamp.maneyself.repository;

import com.enigmacamp.maneyself.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM m_user WHERE email=:email"
    )
    Optional<User> getUserByEmail(@Param("email") String email);
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM m_user WHERE id=:id"
    )
    Optional<User> getUserById(@Param("id") String id);
}
