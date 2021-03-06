/*****************************************************************************************************************
 *
 *	 File			 : IToDoService.java
 *
  *****************************************************************************************************************/


package com.opt.bus.service;

import java.util.List;

import com.opt.bus.dto.ToDoDto;
import com.opt.bus.entity.ToDo;
import com.opt.exception.BusinessException;
import com.opt.exception.NoDataException;

public interface IToDoService {
	
	public void save(ToDo entry) throws BusinessException;

	public void delete(Long id) throws BusinessException;

	public List<ToDo> getToDoListByStatusID() throws NoDataException;
	
	public List<ToDoDto> getToDoList() throws NoDataException ;

	public ToDo getToDo(Long ID) throws NoDataException;

			
}
