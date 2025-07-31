package com.assesment.customer.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.assesment.customer.entity.CustomerEntity;
import com.assesment.response.CustomerResponse;

import jakarta.validation.Valid;

public interface CustomerService {

	public CustomerEntity saveCustomerData(CustomerEntity customerEntity);

	public CustomerEntity getCustomerById(UUID customerId);

	public CustomerEntity getCustomerByName(String customerName);

	public CustomerEntity getCustomerByEmail(String customerEmail);

	public ResponseEntity<CustomerResponse<CustomerEntity>> updateCustomerById(UUID customerId, @Valid CustomerEntity customerEntity);

	public ResponseEntity<CustomerResponse<String>> deleteCustomerById(UUID customerId);

}
