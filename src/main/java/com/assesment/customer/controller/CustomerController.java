package com.assesment.customer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assesment.customer.entity.CustomerEntity;
import com.assesment.customer.service.CustomerService;
import com.assesment.customer.response.CustomerResponse;
import com.assesment.customer.util.ResponseUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/hello")
	public String sayHello() {

		return "Hello World";
	}

	@PostMapping("/saveCustomer")
	public ResponseEntity<CustomerResponse<CustomerEntity>> saveCustomer(@RequestBody @Valid CustomerEntity customerEntity) {

		log.info("POST /saveCustomer called with data: {}",customerEntity);
		  customerEntity= customerService.saveCustomerData(customerEntity);
		log.debug("Customer saved with ID:{}",customerEntity.getId());
		    return ResponseEntity.ok(new CustomerResponse<>(200, "OK", "Customer saved successfully", customerEntity));
		 

	}

	@GetMapping("/getCustomerById")
	public ResponseEntity<CustomerResponse<CustomerEntity>> getCustomerById(@RequestParam UUID customerId) {
		log.info("GET /getCustomerById called with customerId: {}",customerId);
		
		CustomerEntity customerEntity =customerService.getCustomerById(customerId);
		log.debug("Customer fetched : ID ={}",customerId);
		
		return ResponseUtil.buildResponse(HttpStatus.OK,"Customer Fetched Successfully",customerEntity);

	}

	@GetMapping("/getCustomerByName")
	public ResponseEntity<CustomerResponse<CustomerEntity>> getCustomerByName(@RequestParam String customerName) {
		log.info("GET /getCustomerByName called with customerName: {}",customerName);
		CustomerEntity customerEntity=customerService.getCustomerByName(customerName);
		log.debug("Customer fetched : customerName ={}",customerName);

		return ResponseUtil.buildResponse(HttpStatus.OK,"Customer Fetched Successfully",customerEntity);

	}

	@GetMapping("/getCustomerByEmail")
	public ResponseEntity<CustomerResponse<CustomerEntity>> getCustomerByEmail(@RequestParam String customerEmail) {
		log.info("GET /getCustomerByName called with customerEmail: {}",customerEmail);

		CustomerEntity customerEntity= customerService.getCustomerByEmail(customerEmail);
		log.debug("Customer fetched : customerEmail ={}",customerEmail);

		return ResponseUtil.buildResponse(HttpStatus.OK,"Customer Fetched Successfully",customerEntity);
		
	}

	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<CustomerResponse<CustomerEntity>> updateCustomerById(@PathVariable UUID customerId,@RequestBody @Valid CustomerEntity customerEntity) {

		log.info("PUT /updateCustomer/{} called with data: {}",customerId,customerEntity);

		ResponseEntity<CustomerResponse<CustomerEntity>> response=customerService.updateCustomerById(customerId,customerEntity);
		log.debug("Customer updated : {}",customerEntity);
		
		 return response;
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<CustomerResponse<String>>  deleteCustomerById(@PathVariable UUID customerId) {

		log.info("DELETE /deleteCustomer/{} called with data: {}",customerId);

		ResponseEntity<CustomerResponse<String>> response=customerService.deleteCustomerById(customerId);
		log.debug("Customer deleted successfully :ID {}",customerId);

		return response;
	}

}
