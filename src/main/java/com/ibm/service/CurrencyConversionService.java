package com.ibm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.exception.CurrencyConversionNotFoundException;
import com.ibm.exception.DataFoundException;
import com.ibm.exception.NoDataFoundException;
import com.ibm.model.CurrencyConversion;
import com.ibm.respository.CurrencyConversionRepository;

@Service
public class CurrencyConversionService {

	@Autowired
	private CurrencyConversionRepository currencyConversionRepository;

	public CurrencyConversion save(CurrencyConversion currencyConversion) {
		return currencyConversionRepository.saveAndFlush(currencyConversion);
	}

	public CurrencyConversion getCurrencyConversionById(String countryCode) {
		return currencyConversionRepository.findByCountryCode(countryCode)
				.orElseThrow(CurrencyConversionNotFoundException::new);
	}

	public CurrencyConversion updateConversion(CurrencyConversion currencyConversion) {
		
		CurrencyConversion conversion = getCurrencyConversionById(currencyConversion.getCountryCode());
		currencyConversion.setId(conversion.getId());
		return currencyConversionRepository.saveAndFlush(currencyConversion);
	}

	public CurrencyConversion findByCountryCode(String countryCode) {
		Optional<CurrencyConversion> currencyConversion = currencyConversionRepository.findByCountryCode(countryCode);
		if (currencyConversion.isPresent())
			return currencyConversion.get();
		throw new NoDataFoundException();
	}

	public CurrencyConversion findByCountryCode(CurrencyConversion currencyConversion) {
		Optional<CurrencyConversion> data = currencyConversionRepository
				.findByCountryCode(currencyConversion.getCountryCode());
		if (data.isPresent())
			throw new DataFoundException();
		return null;
	}
}
