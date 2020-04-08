package com.devsoftbd.com.ToDoApp.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devsoftbd.com.ToDoApp.dto.StatusCountDTO;
import com.devsoftbd.com.ToDoApp.repositories.TodoItemsRepository;

@Service
public class DashboardService {
	@Autowired
	private TodoItemsRepository todoRepository;

	public List<StatusCountDTO> getTodoCountGroupByStatus() {
		List<StatusCountDTO> list = todoRepository.findCountGroupByStatus();
		if (!StringUtils.isEmpty(list))
			Collections.sort(list, StatusCountDTO.compareByStatus);
		return list;
	}
}
