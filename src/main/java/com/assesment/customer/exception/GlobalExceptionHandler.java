package com.assesment.customer.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.assesment.customer.entity.CustomerEntity;
import com.assesment.customer.response.CustomerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<CustomerResponse<String>> customerNotFoundException(CustomerException ex){
		CustomerResponse<String> response=new CustomerResponse<>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(),ex.getMessage(),null);
		
		return new ResponseEntity<CustomerResponse<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<CustomerResponse<List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        List<String> errors = ex.getBindingResult().getFieldErrors()
	                .stream()
	                .map(error -> error.getDefaultMessage())
	                .collect(Collectors.toList());

	        CustomerResponse<List<String>> response = new CustomerResponse<>(400, "Bad Request", "Validation failed", errors);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

}
