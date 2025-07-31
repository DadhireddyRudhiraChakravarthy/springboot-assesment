package com.assesment.customer.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
	
	   
		@Id
	    private UUID id;
	    
	    @NotBlank(message="Name is required")
	    private String customerName;
	    
	    @NotBlank(message="Email is required")
	    @Email(message="Email should be valid")
	    private String customerEmail;
	    
	    private BigDecimal annualSpend;
	    
	    private  LocalDateTime lastPurchaseDate ;
	    
	    @Transient 
	    private String tier;

	

}
