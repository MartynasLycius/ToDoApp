/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
package com.test.pranto.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.test.pranto.model.ToDo;

public class ToDoDAO {
	private static final Logger LOGGER = Logger.getLogger(ToDoDAO.class.getName());
	private long nextId = 0;

	private static ToDoDAO instance;

	private final HashMap<String, ToDo> todos = new HashMap<>();

	/**
	 * Default constructor. Can be used in place of getInstance()
	 */
	public ToDoDAO() {

	}

	/**
	 * @return a reference to an example facade for Customer objects.
	 */
	public static ToDoDAO getInstance() {
		if (instance == null) {
			instance = new ToDoDAO();
			instance.ensureTestData();
		}
		return instance;
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen", "Koen Johansen",
				"Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson", "Emily Stewart",
				"Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson", "Eileen Walker", "Katelyn Martin",
				"Israel Carlsson", "Quinn Hansson", "Makena Smith", "Danielle Watson", "Leland Harris",
				"Gunner Karlsen", "Jamar Olsson", "Lara Martin", "Ann Andersson", "Remington Andersson",
				"Rene Carlsson", "Elvis Olsen", "Solomon Olsen", "Jaydan Jackson", "Bernard Nilsen" };

		Random r = new Random(0);
		for (String name : names) {
			ToDo toDo = new ToDo();
			toDo.setItemName(name);
			Calendar cal = Calendar.getInstance();
			toDo.setCreateDate(cal.getTime());
			toDo.setLastUpdateTime(cal.getTime());
			int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
			cal.add(Calendar.DAY_OF_MONTH, daysOld);
			toDo.setToDoDate(cal.getTime());
			save(toDo);
		}
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
	 * for new Customer instances.
	 *
	 * @param entry
	 */
	public synchronized void save(ToDo entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId("" + nextId++);
		}
		todos.put(entry.getId(), entry);
	}

	/**
	 * Finds all Customer's that match given filter.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @return list a Customer objects
	 */
	public synchronized List<ToDo> findAll(String stringFilter) {
		ArrayList<ToDo> arrayList = new ArrayList<>();
		for (ToDo contact : todos.values()) {
			boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
					|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
			if (passesFilter) {
				arrayList.add(contact);
			}
		}
		return arrayList;
	}
}