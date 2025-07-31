package com.assesment.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
        CustomerEntity mockCustomer = new CustomerEntity(
                id,
                "Alice",
                "alice@example.com",
                new BigDecimal("1000.00"),
                LocalDateTime.now(),
                null
        );

        when(customerRepository.findById(id)).thenReturn(Optional.of(mockCustomer));

        CustomerEntity result = customerService.getCustomerById(id);

        assertNotNull(result);
        assertEquals("Alice", result.getCustomerName());
        assertEquals("alice@example.com", result.getCustomerEmail());
    }
}
