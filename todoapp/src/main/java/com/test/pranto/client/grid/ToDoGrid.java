package com.test.pranto.client.grid;

import com.test.pranto.client.BeanGrid;
import com.test.pranto.model.ToDo;

public class ToDoGrid extends BeanGrid<ToDo> {

	public ToDoGrid() {
		addColumn("Name", ToDo.PROP_ITEM_NAME);
		addColumn("Description", ToDo.PROP_DESCRIPTION);
		addColumn("Time", ToDo.PROP_TODO_DATE);
	}

	@Override
	public Class getReferenceClass() {
		return ToDo.class;
	}

}
