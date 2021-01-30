package com.shakhawat.todo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.shakhawat.todo.model.ToDo;

@Component
public class ToDoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ToDo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	ToDo toDo = (ToDo) o;

    	// Validation for Name of Task
    	if (toDo.getItemName().trim().isEmpty()) {
            errors.rejectValue("itemName", null, "Name of Task is required");
        }
    	
        if (toDo.getItemName().length() > 200) {
            errors.rejectValue("itemName", null, "Name of Task should be maximum 200 characters.");
        }

        // Validation for Description
        if (toDo.getItemName().trim().isEmpty()) {
            errors.rejectValue("itemDescription", null, "Description of Task is required");
        }
        
        if (toDo.getItemDescription().length() > 500) {
            errors.rejectValue("itemDescription", null, "Description of Task should be maximum 500 characters.");
        }
        
    }
}
