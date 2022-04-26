package com.ibm.exception;

import org.springframework.security.core.AuthenticationException;

public class BearerNotFoundException extends AuthenticationException {
	
	private static final long serialVersionUID = 1049770601059306502L;

	public BearerNotFoundException(String message)
	{
		super(message);
	}
}
