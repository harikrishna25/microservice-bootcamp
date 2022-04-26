package com.ibm.exception;

public class QuantityMismatch extends RuntimeException {

	private static final long serialVersionUID = -4692652471777166426L;

	public QuantityMismatch(String message) {

        super(message);
    }
}
