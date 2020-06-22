package com.backend.boiler.plate.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.boiler.plate.app.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findOneByEmail(String email);
	Optional<User> findOneByUsernameIgnoreCase(String name);
	Optional<User> findOneByUsername(String name);
}
