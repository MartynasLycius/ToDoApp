/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 1:00 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public abstract class BaseModel {
	
	@JsonIgnore
	private String createdBy;
	
	@JsonIgnore
	private String updatedBy;
	
	@JsonIgnore
	private Date createdDate;
	
	@JsonIgnore
	private Date updatedDate;
}
