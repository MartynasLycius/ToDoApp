package com.todo.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * TodoItem form class
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 2020-20-05
 */
@Data
public class TodoItemForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Item name is mandatory")
    @Size(max = 200, message = "Item name text length should not be more then 200 character(s)")
    private String itemName;

    @Size(max = 300, message = "Description text length should not be more then 300 character(s)")
    private String description;

    @NotBlank(message = "Target date is mandatory")
    private String targetDate;
}