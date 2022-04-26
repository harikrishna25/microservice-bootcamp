package com.ibm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibm.model.Catalog;
import com.ibm.model.ERole;
import com.ibm.model.Role;
import com.ibm.repository.CatalogRepository;
import com.ibm.repository.RoleRepository;

@SpringBootApplication
public class AuthenticationServiceApplication implements CommandLineRunner {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CatalogRepository catalogRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role(ERole.ROLE_USER));
		catalogRepository.save(new Catalog("item1", 10.0));
		catalogRepository.save(new Catalog("item2", 5.0));
		catalogRepository.save(new Catalog("item3", 11.0));
		catalogRepository.save(new Catalog("item4", 20.0));
	}

}
