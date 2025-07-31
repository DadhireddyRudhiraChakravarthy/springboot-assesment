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
import com.assesment.response.CustomerResponse;

import java.time.LocalDate;


@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public CustomerEntity saveCustomerData(CustomerEntity customerEntity) {
		customerEntity.setId(UUID.randomUUID());
		customerEntity= customerRepository.save(customerEntity);
		return getTierByAnnualSpend(customerEntity);
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
		// TODO Auto-generated method stub
		Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
		
		if (optionalCustomer.isEmpty()) {
	        throw new CustomerException("Customer with ID " + customerId + " not found");
	    }
		
		return getTierByAnnualSpend(optionalCustomer.get());
				
	}

	@Override
	public CustomerEntity getCustomerByName(String customerName) {
		// TODO Auto-generated method stub
		Optional<CustomerEntity> optionalCustomer = customerRepository.findByCustomerName(customerName);
		
		if (optionalCustomer.isEmpty()) {
	        throw new CustomerException("Customer with Name " + customerName  + " not found");
	    }
		
		return getTierByAnnualSpend(optionalCustomer.get());
	}

	@Override
	public CustomerEntity getCustomerByEmail(String customerEmail) {
		// TODO Auto-generated method stub
		Optional<CustomerEntity> optionalCustomer = customerRepository.findByCustomerEmail(customerEmail);
		

		if (optionalCustomer.isEmpty()) {
	        throw new CustomerException("Customer with Email " + customerEmail  + " not found");
	    }
		return getTierByAnnualSpend(optionalCustomer.get());
	}

	@Override
	public ResponseEntity<CustomerResponse<CustomerEntity>> updateCustomerById(UUID customerId, CustomerEntity customerEntity) {
		// TODO Auto-generated method stub
		 Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
		 if (optionalCustomer.isEmpty()) {
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
		 return ResponseUtil.buildResponse(HttpStatus.OK, "Customer updated successfully",
	                getTierByAnnualSpend(customer));
	       
	}

	@Override
	public ResponseEntity<CustomerResponse<String>> deleteCustomerById(UUID customerId) {
		// TODO Auto-generated method stub
		 Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
	        if (optionalCustomer.isEmpty()) {
	            throw new CustomerException("Customer with ID " + customerId + " not found");
	        }
	      customerRepository.deleteById(customerId);
		 return ResponseUtil.buildResponse(HttpStatus.OK, "Customer deleted successfully", "deleted");
	}
	

}
