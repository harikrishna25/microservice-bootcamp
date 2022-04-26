package com.ibm.model;

public class ResponseStatus {

	private String errorMessage;
	private String status;

	public ResponseStatus(String errorMessage, String status) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
