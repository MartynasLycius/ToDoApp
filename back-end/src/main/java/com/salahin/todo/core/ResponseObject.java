/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 11:56 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseObject implements Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
    private Object data;
	private Integer httpStatusCode;
	private String message;
	
	public ResponseObject(Object data, Integer httpStatusCode, String message) {
		this.data = data;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}
}
