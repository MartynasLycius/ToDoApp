package com.murad.todoapp.service;

import com.murad.todoapp.domain.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
public interface ToDoService {

    ToDo save(ToDo toDo);
    Page<ToDo> findAll(Pageable pageable);
    Page<ToDo> search(
            String OfferName,
            Pageable pageable
    );
    ToDo findOneById(Integer integer);
    void deleteById(Integer Id);

}
