package com.shakhawat.todo.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shakhawat.todo.model.ToDo;
import com.shakhawat.todo.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;

	@Override
	public List<ToDo> getAllToDos() {
		return toDoRepository.findAllByOrderByIsDoneAscDateDesc();
	}

	@Override
	public void saveToDo(ToDo toDo) {
		toDo.setCreatedAt(new Date());
		toDoRepository.save(toDo);
	}

	@Override
	public ToDo findToDoById(String id) {
		Optional<ToDo> optional = toDoRepository.findById(id);
		ToDo toDo = null;
		if (optional.isPresent()) {
			toDo = optional.get();
		} else {
			throw new RuntimeException(" ToDo not found for the ID: " + id);
		}
		return toDo;
	}

	@Override
	public void updateToDo(ToDo toDo) {
		
		if(null != toDo.getId()) {
			Optional<ToDo> optional = toDoRepository.findById(toDo.getId());
			if (optional.isPresent()) {
				toDo.setCreatedAt(optional.get().getCreatedAt());
			}
		}
		toDo.setUpdatedAt(new Date());
		
		toDoRepository.save(toDo);
	}

	@Override
	public Page<ToDo> findPaginated(Pageable pageable) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ToDo> list;
        
        List<ToDo> toDos = toDoRepository.findAllByOrderByIsDoneAscDateDesc();

        if (toDos.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, toDos.size());
            list = toDos.subList(startItem, toIndex);
        }

        Page<ToDo> toDokPage = new PageImpl<ToDo>(list, PageRequest.of(currentPage, pageSize), toDos.size());

        return toDokPage;
	}

}
