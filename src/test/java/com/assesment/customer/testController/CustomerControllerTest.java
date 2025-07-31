package com.assesment.customer.testController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.assesment.customer.controller.CustomerController;
import com.assesment.customer.entity.CustomerEntity;
import com.assesment.customer.service.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private UUID id;
    private CustomerEntity customer;

    @BeforeEach
    void setup() {
        id = UUID.randomUUID();
        customer = new CustomerEntity(id, "harish", "harish@example.com", new BigDecimal("999"), LocalDateTime.now().minusMonths(10), "Gold");
    }

    @Test
    void testGetCustomerById() throws Exception {
        when(customerService.getCustomerById(id)).thenReturn(customer);

        mockMvc.perform(get("/assesment/customer/getCustomerById")
                .param("customerId", id.toString()))
        
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.customerName").value("harish"))
            .andExpect(jsonPath("$.data.customerEmail").value("harish@example.com"))
        .andExpect(jsonPath("$.data.tier").value("Gold"));
    }
    
    @Test
    void testGetCustomerByName() throws Exception {
        when(customerService.getCustomerByName(customer.getCustomerName())).thenReturn(customer);

        mockMvc.perform(get("/assesment/customer/getCustomerByName")
                .param("customerName", customer.getCustomerName()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.customerName").value("harish"))
            .andExpect(jsonPath("$.data.customerEmail").value("harish@example.com"))
        .andExpect(jsonPath("$.data.tier").value("Gold"));
    }
    
    @Test
    void testGetCustomerByEmail() throws Exception {
        when(customerService.getCustomerByEmail(customer.getCustomerEmail())).thenReturn(customer);

        mockMvc.perform(get("/assesment/customer/getCustomerByEmail")
                .param("customerEmail", customer.getCustomerEmail()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.customerName").value("harish"))
            .andExpect(jsonPath("$.data.customerEmail").value("harish@example.com"))
        .andExpect(jsonPath("$.data.tier").value("Gold"));
    }
    
  
	
}
