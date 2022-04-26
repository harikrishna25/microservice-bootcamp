package com.ibm.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibm.config.IConstInterface;
import com.ibm.model.ResponseStatus;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler implements IConstInterface {

	@ExceptionHandler(QuantityMismatch.class)
	public ResponseEntity<Object> handleCityNotFoundException(QuantityMismatch ex, WebRequest req) {
		ResponseStatus status = new ResponseStatus(ex.getMessage(), "FAILURE");
		return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest req) {
		ResponseStatus status = new ResponseStatus(ex.getMessage(), "FAILURE");
		return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest req) {
		ResponseStatus status = new ResponseStatus(ex.getMessage(), "FAILURE");
		return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(BearerNotFoundException.class)
	public ResponseEntity<Object> handleBearerNotFoundException(BearerNotFoundException ex, WebRequest req) {
		ResponseStatus status = new ResponseStatus(ex.getMessage(), "FAILURE");
		return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
