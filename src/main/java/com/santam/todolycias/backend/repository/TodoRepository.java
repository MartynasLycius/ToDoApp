package com.santam.todolycias.backend.repository;

import com.santam.todolycias.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

/**
 * @author Sanower Tamjit
 */
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    @Query(value = "INSERT INTO todo (title,descc,statuss,deadline,ownby) VALUES(?1, ?2, ?3 ,?4, ?5) ",
            nativeQuery = true)
    void minimalSave(String title, String desc, int status, String deadline, int ownBy);

}