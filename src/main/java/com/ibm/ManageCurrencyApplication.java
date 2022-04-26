package com.ibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5001/")
@SpringBootApplication
public class ManageCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageCurrencyApplication.class, args);
	}

}
