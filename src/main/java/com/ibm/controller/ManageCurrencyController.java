package com.ibm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.model.CurrencyConversion;
import com.ibm.model.IConstInterface;
import com.ibm.model.ResponseStatus;
import com.ibm.service.ManageCurrencyService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ManageCurrencyController implements IConstInterface {

	@Autowired
	private ManageCurrencyService manageCurrencyService;

	@PostMapping
	public ResponseEntity<ResponseStatus> addConversionFactor(@RequestBody CurrencyConversion currencyConversion) {

		manageCurrencyService.findByCountryCode(currencyConversion);
		manageCurrencyService.save(currencyConversion);
		ResponseStatus status = new ResponseStatus(EMPTY, SUCCESS);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PutMapping("/{countryCode}")
	public ResponseEntity<CurrencyConversion> updateCurrencyConversion(@PathVariable String countryCode,
			@RequestBody CurrencyConversion currencyConversion) {
		CurrencyConversion result = manageCurrencyService.updateConversion(currencyConversion);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{countryCode}")
	public ResponseEntity<CurrencyConversion> getCurrencyFactor(@PathVariable String countryCode) {
		CurrencyConversion result = manageCurrencyService.findByCountryCode(countryCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public List<CurrencyConversion> getAllconversionFactor() {
		return manageCurrencyService.getALL();
	}
}
