package com.rali.repository;

import com.rali.entity.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("select t from Todo t order by t.createdAt desc, t.modifiedAt desc ")
    List<Todo> findAllToDos(Pageable pageable);

}
