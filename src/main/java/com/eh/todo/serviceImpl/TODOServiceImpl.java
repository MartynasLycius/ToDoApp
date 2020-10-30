package com.eh.todo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eh.todo.model.TODOModel;
import com.eh.todo.repository.TODORepository;
import com.eh.todo.service.TODOService;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Service
public class TODOServiceImpl implements TODOService {

    @Autowired
    private TODORepository repository;

    /**
     * This method is used to get all the records from the database
     *
     * @return  : This method returns list of model
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @Override
    public List<TODOModel> getAllTODO() {
        return repository.findAll();
    }

    /**
     * This method is used to get single record from the database
     *
     * @param   : id, use it for get data form database by id
     * @return  : This method returns single model
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @Override
    public TODOModel getTODOById(long id) {
        Optional<TODOModel> optional = repository.findById(id);
        TODOModel todo = null;
        //checking data is present or not
        if (optional.isPresent()) {
            todo = optional.get();
            
            //Date completedDate = DateUtil.parse(todo.getCompletedDate().toString(), DateUtil.YYYY_MM_DD);
            //Date createDate = DateUtil.parse(todo.getCreateDate().toString(), DateUtil.YYYY_MM_DD);
            //todo.setCompletedDate(completedDate);
            //todo.setCreateDate(createDate);
        } else {
            throw new RuntimeException(" TODO not found for id :: " + id);
        }
        return todo;
    }

    /**
     * This method is used to save record to database
     *
     * @param   : todo, use it for save model data to database
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @Override
    public void saveTODO(TODOModel todo) {

        //Date completedDate = DateUtil.parse(todo.getCompletedDate().toString(), DateUtil.YYYY_MM_DD);
        //Date createDate = DateUtil.parse(todo.getCreateDate().toString(), DateUtil.YYYY_MM_DD);

        //todo.setCompletedDate(completedDate);
        //todo.setCreateDate(createDate);
        repository.save(todo);
    }

    /**
     * This method is used to delete single record from the database
     *
     * @param   : id, use it for delete data form database by id
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @Override
    public void deleteTODOById(long id) {
        repository.deleteById(id);
    }

}
