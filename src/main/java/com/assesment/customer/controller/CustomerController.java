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
import com.assesment.response.CustomerResponse;
import com.assesment.customer.util.ResponseUtil;

import jakarta.validation.Valid;

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

		  customerEntity= customerService.saveCustomerData(customerEntity);
		    return ResponseEntity.ok(new CustomerResponse<>(200, "OK", "Customer saved successfully", customerEntity));

	}

	@GetMapping("/getCustomerById")
	public ResponseEntity<CustomerResponse<CustomerEntity>> getCustomerById(@RequestParam UUID customerId) {

		CustomerEntity customerEntity =customerService.getCustomerById(customerId);
		return ResponseUtil.buildResponse(HttpStatus.OK,"Customer Fetched Successfully",customerEntity);

	}

	@GetMapping("/getCustomerByName")
	public ResponseEntity<CustomerResponse<CustomerEntity>> getCustomerByName(@RequestParam String customerName) {

		CustomerEntity customerEntity=customerService.getCustomerByName(customerName);
		return ResponseUtil.buildResponse(HttpStatus.OK,"Customer Fetched Successfully",customerEntity);

	}

	@GetMapping("/getCustomerByEmail")
	public ResponseEntity<CustomerResponse<CustomerEntity>> getCustomerByEmail(@RequestParam String customerEmail) {

		CustomerEntity customerEntity= customerService.getCustomerByEmail(customerEmail);
		return ResponseUtil.buildResponse(HttpStatus.OK,"Customer Fetched Successfully",customerEntity);
		
	}

	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<CustomerResponse<CustomerEntity>> updateCustomerById(@PathVariable UUID customerId,@RequestBody @Valid CustomerEntity customerEntity) {

		 return customerService.updateCustomerById(customerId,customerEntity);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<CustomerResponse<String>>  deleteCustomerById(@PathVariable UUID customerId) {

		return customerService.deleteCustomerById(customerId);
	}

}
