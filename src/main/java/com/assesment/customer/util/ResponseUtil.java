package com.assesment.customer.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assesment.response.CustomerResponse;

public class ResponseUtil {

	public static <T> ResponseEntity<CustomerResponse<T>> buildResponse (HttpStatus httpStatus,String message,T data){
		
		CustomerResponse<T> response=new CustomerResponse<>(httpStatus.value(),httpStatus.name(),message,data);
		return new ResponseEntity<>(response,httpStatus);
	}
}
