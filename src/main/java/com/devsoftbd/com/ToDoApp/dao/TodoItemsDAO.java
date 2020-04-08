package com.devsoftbd.com.ToDoApp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.devsoftbd.com.ToDoApp.enums.TodoItemStatus;
import com.devsoftbd.com.ToDoApp.models.TodoItemsModel;

@Repository
public class TodoItemsDAO {
	@PersistenceContext
	private EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(TodoItemsDAO.class.getName());

	public List<TodoItemsModel> getTodoListFromDynamicCriteria(Map<String, Object> criteria) {
		List<TodoItemsModel> list = null;
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<TodoItemsModel> query = cb.createQuery(TodoItemsModel.class);
			Root<TodoItemsModel> rows = query.from(TodoItemsModel.class);
			Map<String, Predicate> predicatesMap = new HashMap<>(5);

			if (criteria.containsKey("status")) {
				predicatesMap.put("status", cb.equal(rows.get("status"), (TodoItemStatus) criteria.get("status")));
			}
			if (criteria.containsKey("startDate")) {
				predicatesMap.put("creationDateTime",
						cb.greaterThanOrEqualTo(rows.get("creationDateTime"), (Date) criteria.get("startDate")));
			}
			if (criteria.containsKey("endDate")) {
				predicatesMap.put("creationDateTime",
						cb.lessThanOrEqualTo(rows.get("creationDateTime"), (Date) criteria.get("endDate")));
			}
			query.orderBy(cb.desc(rows.get("creationDateTime")));
			query.select(rows).where(predicatesMap.values().toArray(new Predicate[] {}));
			list = entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return list;
	}
}
