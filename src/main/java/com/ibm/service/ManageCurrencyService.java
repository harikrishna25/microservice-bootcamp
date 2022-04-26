package com.ibm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.exception.CurrencyConversionNotFoundException;
import com.ibm.exception.DataFoundException;
import com.ibm.exception.NoDataFoundException;
import com.ibm.model.CurrencyConversion;
import com.ibm.respository.ManageCurrencyRepository;

@Service
public class ManageCurrencyService {

	@Autowired
	private ManageCurrencyRepository manageCurrencyRepository;

	public CurrencyConversion save(CurrencyConversion currencyConversion) {
		return manageCurrencyRepository.saveAndFlush(currencyConversion);
	}

	public CurrencyConversion getCurrencyConversionById(String countryCode) {
		return manageCurrencyRepository.findByCountryCode(countryCode)
				.orElseThrow(CurrencyConversionNotFoundException::new);
	}

	public CurrencyConversion updateConversion(CurrencyConversion currencyConversion) {

		CurrencyConversion conversion = getCurrencyConversionById(currencyConversion.getCountryCode());
		currencyConversion.setId(conversion.getId());
		return manageCurrencyRepository.saveAndFlush(currencyConversion);
	}

	public CurrencyConversion findByCountryCode(String countryCode) {
		Optional<CurrencyConversion> currencyConversion = manageCurrencyRepository.findByCountryCode(countryCode);
		if (currencyConversion.isPresent())
			return currencyConversion.get();
		throw new NoDataFoundException();
	}

	public CurrencyConversion findByCountryCode(CurrencyConversion currencyConversion) {
		Optional<CurrencyConversion> data = manageCurrencyRepository
				.findByCountryCode(currencyConversion.getCountryCode());
		if (data.isPresent())
			throw new DataFoundException();
		return null;
	}

	public List<CurrencyConversion> getALL() {
		return manageCurrencyRepository.findAll();
	}
}
