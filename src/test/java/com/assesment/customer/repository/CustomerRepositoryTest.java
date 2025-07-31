package com.assesment.customer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assesment.customer.entity.CustomerEntity;

@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveAndFind() {
        UUID id = UUID.randomUUID();
        CustomerEntity customer = new CustomerEntity(id, "TestUser", "test@example.com",
                new BigDecimal("2000.00"), LocalDateTime.now(), null);

        customerRepository.save(customer);

        Optional<CustomerEntity> found = customerRepository.findById(id);
        assertTrue(found.isPresent());
        assertEquals("TestUser", found.get().getCustomerName());
    }
}
