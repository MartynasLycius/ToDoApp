package com.opt.bus.todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.opt.bus.dto.ToDoDto;
import com.opt.bus.entity.ToDo;
import com.opt.bus.service.IToDoService;

import com.opt.common.enumeration.Status;
import com.opt.common.persistence.CollectionBean;
import com.opt.common.persistence.PersistenceBean;

import com.opt.exception.BusinessException;
import com.opt.exception.NoDataException;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ToDoService implements IToDoService {

	@Inject
	private Properties properties;

	@Inject
	private PersistenceBean persistenceBean;

	@Inject
	private CollectionBean collectionBean;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(ToDo todo) throws BusinessException {

		if (todo.getId() == null) {
			persistenceBean.save(todo);
		} else {
			persistenceBean.update(todo);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Long id) throws BusinessException {
		//persistenceBean.delete(todo);
		  persistenceBean.delete(ToDo.class, id);
	}

	public ToDo getToDo(Long id) throws NoDataException {
		String queryString = properties.getProperty("0221JPA01");
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return collectionBean.persistenceFind(ToDo.class, queryString, params);
	}

	public List<ToDo> getToDoListByStatusID() throws NoDataException {
		String queryString = properties.getProperty("0221JPA02");
		Map<String, Object> params = new ConcurrentHashMap<>();
		params.put("statusId", Status.ACTIVE.getValue());
		List<ToDo> toDoList = collectionBean.persistenceList(ToDo.class, queryString, params);
		return toDoList;
	}
	
	public List<ToDoDto> getToDoList() throws NoDataException {
		String queryString = properties.getProperty("0221SQL01");
		Map<String, Object> params = new ConcurrentHashMap<>();
		params.put("statusId", Status.ACTIVE.getValue());
		List<ToDoDto> toDoList = collectionBean.nativeList(ToDoDto.class,queryString,params);
		return toDoList;
	}

}
