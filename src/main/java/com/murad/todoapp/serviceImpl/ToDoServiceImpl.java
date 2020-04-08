package com.murad.todoapp.serviceImpl;
import com.murad.todoapp.domain.ToDo;
import com.murad.todoapp.repository.ToDoRepository;
import com.murad.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    @Override
    public ToDo save(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @Override
    public Page<ToDo> findAll(Pageable pageable) {
        return toDoRepository.findAll(pageable);
    }
    @Override
    public Page<ToDo> search(String OfferName, Pageable pageable) {
        return toDoRepository.findByItemNameContainsAllIgnoreCase(OfferName,pageable);
    }

    @Override
    public ToDo findOneById(Integer integer) {
        return toDoRepository.findOneById(integer);
    }

    @Override
    public void deleteById(Integer Id) {
        toDoRepository.deleteById(Id);
    }
}
