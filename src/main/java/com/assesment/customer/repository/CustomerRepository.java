package com.assesment.customer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assesment.customer.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,UUID> {
	
	public Optional<CustomerEntity> findByCustomerName(String customerName);
	
	public Optional<CustomerEntity> findByCustomerEmail(String customerEmail);
	

}
