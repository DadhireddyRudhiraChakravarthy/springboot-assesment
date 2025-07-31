package com.assesment.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assesment.customer.entity.CustomerEntity;
import com.assesment.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)

public class CustomerServiceTestImpl {
	@InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testGetCustomerById() {
        UUID id = UUID.randomUUID();
        CustomerEntity customer = new CustomerEntity();
        customer.setId(id);
        customer.setCustomerName("harish");
        customer.setCustomerEmail("harish@example.com");
        customer.setAnnualSpend(BigDecimal.valueOf(12000));
        customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(3)); // within 6 months


        LocalDate purchaseDate = customer.getLastPurchaseDate().toLocalDate();
        String tier = customer.getAnnualSpend().compareTo(BigDecimal.valueOf(10000)) >= 0 && purchaseDate.isAfter(LocalDate.now().minusMonths(6)) ? "Platinum" :
                customer.getAnnualSpend().compareTo(BigDecimal.valueOf(1000)) > 0 && customer.getAnnualSpend().compareTo(BigDecimal.valueOf(10000)) < 0 && purchaseDate.isAfter(LocalDate.now().minusMonths(12)) ? "Gold" :
                "Silver";

        customer.setTier(tier);
        
        
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        CustomerEntity result = customerService.getCustomerById(id);

        assertNotNull(result);
        assertEquals("Alice", result.getCustomerName());
        assertEquals("alice@example.com", result.getCustomerEmail());
    }
}
