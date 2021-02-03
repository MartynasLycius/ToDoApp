package com.todo.controller;

import com.todo.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/// this repo connected to History table  which stores the data of current history list of tasks
public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findBytaskNameStartsWithIgnoreCase(String taskName);
}
