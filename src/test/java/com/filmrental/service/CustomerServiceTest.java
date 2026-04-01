package com.filmrental.service;

import com.filmrental.entity.Customer;
import com.filmrental.dto.response.CustomerResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.CustomerMapper;
import com.filmrental.repository.CustomerRepository;
import com.filmrental.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock private CustomerRepository customerRepository;
    @Mock private CustomerMapper customerMapper;
    @InjectMocks private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setActive(1);
    }

    @Test
    void getCustomerById_customerExists_returnsCustomerResponse() {
        CustomerResponse expected = CustomerResponse.builder()
                .customerId(1).firstName("John").lastName("Doe").build();

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerMapper.toResponse(customer)).thenReturn(expected);

        CustomerResponse result = customerService.getCustomerById(1);

        assertNotNull(result);
        assertEquals(1, result.getCustomerId());
        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    void getCustomerById_customerNotFound_throwsResourceNotFoundException() {
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> customerService.getCustomerById(99));

        verify(customerRepository, times(1)).findById(99);
    }
}