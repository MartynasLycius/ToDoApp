/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 1:10 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salahin.todo.core.BaseModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
public class TodoModel extends BaseModel {
	private UUID id;

	@Size(min=1, max=100)
	@NotNull(message = "Name is mandatory")
	private String itemName;

	@Size(max=500)
	private String description;
	
	@NotNull(message = "Date is mandatory")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;

	private String noteImage;
}
