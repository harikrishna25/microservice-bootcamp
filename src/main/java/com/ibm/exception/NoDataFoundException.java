package com.ibm.exception;

public class NoDataFoundException extends RuntimeException {

  
	private static final long serialVersionUID = -4692652471777166426L;

	public NoDataFoundException() {

        super("No data found");
    }
}
