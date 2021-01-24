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
import java.util.Date;
import java.util.UUID;

@Data
public class TodoModel extends BaseModel {
	private UUID id;
	private String itemName;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
}
