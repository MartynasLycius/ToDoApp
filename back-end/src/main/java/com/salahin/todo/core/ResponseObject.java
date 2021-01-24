/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 11:56 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.core;

import lombok.Data;

@Data
public class ResponseObject {
    private Object data;
	private Integer httpStatusCode;
	private String message;
	
	public ResponseObject(Object data, Integer httpStatusCode, String message) {
		this.data = data;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}
}
