package com.test.pranto.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.test.pranto.model.ToDo;
import com.test.pranto.model.base.BaseToDoDAO;

public class ToDoDAO extends BaseToDoDAO {

	/**
	 * Default constructor. Can be used in place of getInstance()
	 */
	public ToDoDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<ToDo> findToDos(String name) {

		try (Session session = createNewSession()) {
			Criteria criteria = session.createCriteria(ToDo.class);
			if (name != null && name != "") {
				criteria.add(Restrictions.ilike(ToDo.PROP_ITEM_NAME, name, MatchMode.ANYWHERE));
			}

			return criteria.list();
		}
	}

}