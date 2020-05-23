package com.santam.todolycias.backend.repository;

import java.util.Optional;

import com.santam.todolycias.backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sanower Tamjit
 *
 */
public interface UserRepository extends JpaRepository<Users, Integer>
{
    Optional<Users> findByEmail(String email);
}