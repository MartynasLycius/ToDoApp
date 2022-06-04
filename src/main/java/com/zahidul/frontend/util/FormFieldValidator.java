package com.zahidul.frontend.util;

import org.apache.commons.lang3.StringUtils;

import com.zahidul.backend.model.ToDoItem;

public class FormFieldValidator {

	public static boolean hasEmptyField(ToDoItem item) {

		return (StringUtils.isEmpty(item.getItemName()) || StringUtils.isEmpty(item.getItemDescription())
				|| item.getItemDate() == null);
	}
}
