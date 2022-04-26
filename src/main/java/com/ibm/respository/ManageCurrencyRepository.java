package com.ibm.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.model.CurrencyConversion;

@Repository
public interface ManageCurrencyRepository extends JpaRepository<CurrencyConversion, Long> {

	Optional<CurrencyConversion> findByCountryCode(String countryCode);

}
