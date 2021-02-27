package com.opt.todo;

import com.opt.exception.BusinessException;
import com.opt.exception.ErrorCode;

public class ToDoException extends BusinessException {

	private static final long serialVersionUID = 8905786432118667486L;

	public ToDoException() {
		super();
	}

	public ToDoException(final ErrorCode errorCode) {
		super(errorCode);
	}

	public ToDoException(final ErrorCode errorCode, final String message) {
		super(errorCode, message);
	}

	public ToDoException(final ErrorCode errorCode, final String message, final Throwable throwable) {
		super(errorCode, message, throwable);
	}

	public ToDoException(final ErrorCode errorCode, final Throwable throwable) {
		super(errorCode, throwable);
	}

}
