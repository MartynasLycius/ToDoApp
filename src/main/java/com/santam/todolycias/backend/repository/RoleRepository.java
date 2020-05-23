package com.santam.todolycias.backend.repository;

import com.santam.todolycias.backend.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sanower Tamjit
 *
 */
public interface RoleRepository extends JpaRepository<Roles, Integer>
{
}