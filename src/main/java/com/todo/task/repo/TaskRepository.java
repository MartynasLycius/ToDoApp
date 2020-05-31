package com.todo.task.repo;

import com.todo.task.entity.Task;
import com.todo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDate(LocalDate date);
    List<Task> findByTaskOwner(User user);
    List<Task> findByDateAndTaskOwner(LocalDate date, User user);
}
