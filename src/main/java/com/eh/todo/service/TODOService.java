package com.eh.todo.service;

import java.util.List;

import com.eh.todo.model.TODOModel;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public interface TODOService {

    List<TODOModel> getAllTODO();
    TODOModel getTODOById(long id);

    void saveTODO(TODOModel todo);
    void deleteTODOById(long id);
}
