/*****************************************************************************************************************
 *
 *	 File			 : NoDataException.java
 *
  *****************************************************************************************************************/


package com.opt.exception;


/*
 * This class is  NoDataException class   which is used 
 *  when no data found from db. 
 */
public class NoDataException extends Exception {

	private static final long serialVersionUID = -9071908695250414603L;

	public NoDataException() {
		super("Data not found.");
	}

	public NoDataException(final String message) {
		super(message);
	}

}
