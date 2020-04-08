package com.murad.todoapp.repository;


import com.murad.todoapp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User save(User user);
  User findOneById(Integer id);
  Page<User> findByEmailContainsOrRoleContains(String email, String role,Pageable pageable);
  List<User>findAllByRole(String role);
  int countByEmail(String email);

  User findByEmail(String email);

 
  void deleteById(Integer Id);
}
