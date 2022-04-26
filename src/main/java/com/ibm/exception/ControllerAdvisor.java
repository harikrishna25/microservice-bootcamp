package com.ibm.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibm.model.IConstInterface;
import com.ibm.model.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler implements IConstInterface {

	@ExceptionHandler(CurrencyConversionNotFoundException.class)
	public ResponseEntity<Object> handleCityNotFoundException(CurrencyConversionNotFoundException ex, WebRequest req) {
		ResponseStatus status = new ResponseStatus(EMPTY, FAILURE);
		return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataFoundException.class)
	public ResponseEntity<Object> handleCityNotFoundException(DataFoundException ex, WebRequest req) {
		ResponseStatus status = new ResponseStatus("Currency Code Found", FAILURE);
		return new ResponseEntity<>(status, HttpStatus.FOUND);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> handleNodataFoundException(NoDataFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No currenct code found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
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
