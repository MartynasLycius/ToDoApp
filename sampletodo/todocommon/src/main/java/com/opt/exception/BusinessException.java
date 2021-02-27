package com.opt.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public abstract class BusinessException extends Exception {

	private static final long serialVersionUID = -4054493009652021045L;

	private final List<ErrorCode> errorCodeList = new ArrayList<>();
	private final Map<String, Object> properties = new TreeMap<>();

	protected BusinessException() {
		super();
	}

	protected BusinessException(final ErrorCode errorCode) {
		super();
		errorCodeList.add(errorCode);
	}

	protected BusinessException(final ErrorCode errorCode, final String message) {
		super(message);
		errorCodeList.add(errorCode);
	}

	protected BusinessException(final ErrorCode errorCode, final String message, final Throwable throwable) {
		super(message, throwable);
		errorCodeList.add(errorCode);
	}

	protected BusinessException(final ErrorCode errorCode, final Throwable throwable) {
		super(throwable);
		errorCodeList.add(errorCode);
	}

	public List<ErrorCode> get() {
		return errorCodeList;
	}

	public Object get(final String key) {
		return properties.get(key);
	}

	public void set(final ErrorCode errorCode) {
		errorCodeList.add(errorCode);
	}

	public void set(final String key, final Object value) {
		properties.put(key, value);
	}

}
