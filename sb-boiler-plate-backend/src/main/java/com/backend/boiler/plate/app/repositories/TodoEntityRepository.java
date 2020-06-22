package com.backend.boiler.plate.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.boiler.plate.app.models.Todo;

public interface TodoEntityRepository extends JpaRepository<Todo, Long> {

}
