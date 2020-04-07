package com.murad.todoapp.repository;



import com.murad.todoapp.domain.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Integer> {


    ToDo save(ToDo toDo);

    List<ToDo> findAll();
    Page<ToDo> findAll(Pageable pageable);

    List<ToDo> findAllByOrderByIdDesc();
    Page<ToDo> findAllByOrderByIdDesc(Pageable pageable);

    List<ToDo> findByEndDateAfterOrderByIdDesc(LocalDateTime endDate);


    Page<ToDo> findByItemNameContainsAllIgnoreCase(
            String Name,
            Pageable pageable
    );


    ToDo findOneById(Integer integer);

    void deleteById(Integer Id);
}
