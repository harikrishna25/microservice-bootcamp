package com.ibm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.model.OrderDetails;

public interface OrderDetailsRepository  extends JpaRepository<OrderDetails, Integer> {

}
