package com.ibm.exception;

public class DataFoundException extends RuntimeException {

  
	private static final long serialVersionUID = -4692652471777166426L;

	public DataFoundException() {

        super("currency code found");
    }
}
