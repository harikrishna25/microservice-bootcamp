package com.ibm.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.exception.RestTemplateResponseErrorHandler;
import com.ibm.model.CurrencyConversion;
import com.ibm.model.IConstInterface;

@RestController

@RequestMapping("/api")
public class CurrencyConversionController implements IConstInterface {

	private RestTemplate restTemplate;

	@Value("${conversionurl}")
	private String conversionurl;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@GetMapping("/conversion")
	public ResponseEntity<CurrencyConversion> addConversionFactor(
			@RequestParam(name = "countryCode") String countryCode, @RequestParam(name = "amount") Double amount) {

		CurrencyConversion response = restTemplate.getForObject(conversionurl + "/api/" + countryCode,
				CurrencyConversion.class);
		Double value = (Double.valueOf(response.getConversionFactor()) * amount);
		response.setConvertedAmount(value);
		response.setAmount(amount);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/conversion/{countryCode}/{amount}")
	public ResponseEntity<CurrencyConversion> getConversionFactor(@PathVariable String countryCode,
			@PathVariable Double amount) {

		CurrencyConversion response = restTemplate.getForObject(conversionurl + "/api/" + countryCode,
				CurrencyConversion.class);

		Double value = (Double.valueOf(response.getConversionFactor()) * amount);
		response.setConvertedAmount(value);
		response.setAmount(amount);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/ping")
	public ResponseEntity<String> ping() {
		return new ResponseEntity<>("Test2", HttpStatus.OK);
	}

	@PostConstruct
	private void buildRestTemplate() {
		restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler());
		restTemplate = restTemplateBuilder.build();

	}
}
