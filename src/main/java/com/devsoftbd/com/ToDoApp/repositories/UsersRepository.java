package com.devsoftbd.com.ToDoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoftbd.com.ToDoApp.models.UsersModel;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

}
