package com.murad.todoapp.repository;

import com.murad.todoapp.domain.CustomLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
public interface CustomLogRepositiry extends JpaRepository<CustomLog, Integer> {
}
