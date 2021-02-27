package com.opt.todo;

import com.opt.exception.ErrorCode;

public enum ToDoErrorCode implements ErrorCode {

	BR_021101("021101"), BR_022201("022201"), BR_022202("022202");

	private final String code;

	private ToDoErrorCode(final String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

}
