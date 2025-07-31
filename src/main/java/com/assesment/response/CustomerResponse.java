package com.assesment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse<T> {

	    private int statusCode;
	    private String status;
	    private String message;
	    private T data;
}
