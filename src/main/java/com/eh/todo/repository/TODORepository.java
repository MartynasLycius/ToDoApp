package com.eh.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eh.todo.model.TODOModel;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Repository
public interface TODORepository extends JpaRepository<TODOModel, Long>{

}
