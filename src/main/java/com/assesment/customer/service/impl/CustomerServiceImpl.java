package com.assesment.customer.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assesment.customer.entity.CustomerEntity;
import com.assesment.customer.exception.CustomerException;
import com.assesment.customer.repository.CustomerRepository;
import com.assesment.customer.service.CustomerService;
import com.assesment.customer.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

import com.assesment.customer.response.CustomerResponse;

import java.time.LocalDate;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public CustomerEntity saveCustomerData(CustomerEntity customerEntity) {
		log.info("Saving customer: {}", customerEntity);

		customerEntity.setId(UUID.randomUUID());
		customerEntity= customerRepository.save(customerEntity);
		
		CustomerEntity savedEntity=getTierByAnnualSpend(customerEntity);
		log.debug("Customer saved with tier: {}", savedEntity.getTier());
		return savedEntity;
	}
	
	public CustomerEntity  getTierByAnnualSpend(CustomerEntity customerEntity) {
		
		
		LocalDate purchaseDate=customerEntity.getLastPurchaseDate() !=null ? customerEntity.getLastPurchaseDate().toLocalDate():null;
		
		
		customerEntity.setTier(customerEntity.getAnnualSpend().compareTo(BigDecimal.valueOf(10000)) >= 0 && purchaseDate.isAfter(LocalDate.now().minusMonths(6)) ? "Platinum" :
			customerEntity.getAnnualSpend().compareTo(BigDecimal.valueOf(1000)) > 0 && customerEntity.getAnnualSpend().compareTo(BigDecimal.valueOf(10000)) < 0 && purchaseDate.isAfter(LocalDate.now().minusMonths(12)) ? "Gold" :
		    "Silver"
		);
		
		return customerEntity;
	}

	@Override
	public CustomerEntity getCustomerById(UUID customerId) {
		
		log.info("Fetching customer by ID: {}", customerId);
		Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
		
		if (optionalCustomer.isEmpty()) {
			log.warn("Customer with ID {} not found", customerId);
	        throw new CustomerException("Customer with ID " + customerId + " not found");
	    }
		
		CustomerEntity customerEntity=getTierByAnnualSpend(optionalCustomer.get());
		log.debug("Customer fetched: {}", customerId);
		return customerEntity;
				
	}

	@Override
	public CustomerEntity getCustomerByName(String customerName) {
		// TODO Auto-generated method stub
		log.info("Fetching customer by NAME: {}", customerName);
		Optional<CustomerEntity> optionalCustomer = customerRepository.findByCustomerName(customerName);
		
		if (optionalCustomer.isEmpty()) {
			log.warn("Customer with NAME {} not found", customerName);
	        throw new CustomerException("Customer with Name " + customerName  + " not found");
	    }
		
		CustomerEntity customerEntity=getTierByAnnualSpend(optionalCustomer.get());
		log.debug("Customer fetched: {}", customerName);
		return customerEntity;
	}

	@Override
	public CustomerEntity getCustomerByEmail(String customerEmail) {

		log.info("Fetching customer by NAME: {}", customerEmail);
		Optional<CustomerEntity> optionalCustomer = customerRepository.findByCustomerEmail(customerEmail);
		

		if (optionalCustomer.isEmpty()) {
			log.warn("Customer with EMAIL {} not found", customerEmail);
	        throw new CustomerException("Customer with Email " + customerEmail  + " not found");
	    }
		CustomerEntity customerEntity=getTierByAnnualSpend(optionalCustomer.get());
		log.debug("Customer fetched: {}", customerEmail);
		return customerEntity;
	}

	@Override
	public ResponseEntity<CustomerResponse<CustomerEntity>> updateCustomerById(UUID customerId, CustomerEntity customerEntity) {
		
		log.info("Updating customer ID: {}", customerId);
		 Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
		 if (optionalCustomer.isEmpty()) {
			 log.warn("Customer with ID {} not found for update", customerId);
	            throw new CustomerException("Customer with ID " + customerId + " not found");
	        }
		 
		 CustomerEntity customer=optionalCustomer.get();
		 customer.setCustomerName(customerEntity.getCustomerName());
		 customer.setCustomerEmail(customerEntity.getCustomerEmail());
		 
		 customer.setAnnualSpend(customerEntity.getAnnualSpend()!=null?customerEntity.getAnnualSpend()
				 :customer.getAnnualSpend());
		 
		 customer.setLastPurchaseDate(customerEntity.getLastPurchaseDate()!=null?customerEntity.getLastPurchaseDate()
				 :customer.getLastPurchaseDate());
		 

		 customer= customerRepository.save(customer);
		 
		 log.debug("Customer updated: {}", customer);
		 
		 return ResponseUtil.buildResponse(HttpStatus.OK, "Customer updated successfully",
	                getTierByAnnualSpend(customer));
	       
	}

	@Override
	public ResponseEntity<CustomerResponse<String>> deleteCustomerById(UUID customerId) {
		
		log.info("Deleteing  customer by ID: {}", customerId);
		 Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
	        if (optionalCustomer.isEmpty()) {
	        	log.warn("Customer with ID {} not found for deletion", customerId);
	            throw new CustomerException("Customer with ID " + customerId + " not found");
	        }
	      customerRepository.deleteById(customerId);
	      log.debug("Customer deleted successfully: {}", customerId);
		 return ResponseUtil.buildResponse(HttpStatus.OK, "Customer deleted successfully", "deleted");
	}
	

}
