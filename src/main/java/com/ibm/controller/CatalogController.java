package com.ibm.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.config.IConstInterface;
import com.ibm.exception.QuantityMismatch;
import com.ibm.model.Catalog;
import com.ibm.model.CatalogRequest;
import com.ibm.model.OrderDetails;
import com.ibm.model.OrderStatus;
import com.ibm.model.Users;
import com.ibm.repository.CatalogRepository;
import com.ibm.repository.OrderDetailsRepository;
import com.ibm.repository.UserRepository;
import com.ibm.utils.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/catalog")
public class CatalogController implements IConstInterface {
	@Autowired
	CatalogRepository catalogRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/all")
	public List<Catalog> getAllCatlogItems() {
		return catalogRepository.findAll();
	}

	@PostMapping("/order")
	public ResponseEntity<?> submitCatalog(HttpServletRequest request,
			@Valid @RequestBody CatalogRequest catalogRequest) {

		OrderStatus status = new OrderStatus();
		OrderDetails details = new OrderDetails();
		Optional<Catalog> catalogItem = catalogRepository.findByItem(catalogRequest.getItem());

		String jwt = parseJwt(request);
		if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
			String username = jwtUtils.getUserNameFromJwtToken(jwt);

			Users user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

			if (!catalogItem.isEmpty()) {
				Catalog catalog = catalogItem.get();
				if (catalogRequest.getQuantity() <= catalog.getQuantity()) {
					details.setTransactionToken(Base64.getEncoder().encodeToString(
							(catalog.getItem() + UNDERSCORE + catalogRequest.getQuantity()).getBytes()));
					details.setUserToken(user.getUserToken());
					details.setServiceToken(RandomStringUtils.randomAlphabetic(20));
					orderDetailsRepository.save(details);
				} else
					throw new QuantityMismatch("Quantity Mismatch::" + catalogRequest.getQuantity());
			}
		}
		status.setStatus("Success");
		status.setTransactionId(details.getServiceToken());
		return ResponseEntity.ok().body(status);
	}

	private String parseJwt(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtFromCookies(request);
		return jwt;
	};
}
