package com.assesment.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.assesment.customer.entity.CustomerEntity;
import com.assesment.response.CustomerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<CustomerResponse<String>> customerNotFoundException(CustomerException ex){
		CustomerResponse<String> response=new CustomerResponse<>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),ex.getMessage(),null);
		
		return new ResponseEntity<CustomerResponse<String>>(response,HttpStatus.NOT_FOUND);
	}
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<CustomerResponse<String>> handleGenericException(CustomerException ex){
//		CustomerResponse<String> response=new CustomerResponse<>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),"Something Went Wrong",null);
//		
//		return new ResponseEntity<CustomerResponse<String>>(response,HttpStatus.NOT_FOUND);
//	}
}
