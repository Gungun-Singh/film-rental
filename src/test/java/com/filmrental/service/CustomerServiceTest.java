package com.filmrental.service;

import com.filmrental.entity.Customer;
import com.filmrental.entity.Rental;
import com.filmrental.entity.Payment;
import com.filmrental.dto.response.CustomerResponse;
import com.filmrental.dto.response.RentalResponse;
import com.filmrental.dto.response.PaymentResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.CustomerMapper;
import com.filmrental.mapper.RentalMapper;
import com.filmrental.mapper.PaymentMapper;
import com.filmrental.repository.CustomerRepository;
import com.filmrental.repository.RentalRepository;
import com.filmrental.repository.PaymentRepository;
import com.filmrental.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock private CustomerRepository customerRepository;
    @Mock private CustomerMapper customerMapper;
    @Mock private RentalRepository rentalRepository;
    @Mock private RentalMapper rentalMapper;
    @Mock private PaymentRepository paymentRepository;  // added
    @Mock private PaymentMapper paymentMapper;          // added
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

    // previous 4 tests unchanged ...

    @Test
    void getCustomerPayments_customerExists_returnsPaymentList() {
        Payment payment = new Payment();
        payment.setPaymentId(20);
        payment.setCustomer(customer);
        payment.setAmount(new BigDecimal("4.99"));

        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(20).amount(new BigDecimal("4.99")).build();

        when(customerRepository.existsById(1)).thenReturn(true);
        when(paymentRepository.findByCustomer_CustomerId(1)).thenReturn(List.of(payment));
        when(paymentMapper.toResponse(payment)).thenReturn(paymentResponse);

        List<PaymentResponse> result = customerService.getCustomerPayments(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(20, result.get(0).getPaymentId());
    }

    @Test
    void getCustomerPayments_customerNotFound_throwsResourceNotFoundException() {
        when(customerRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> customerService.getCustomerPayments(99));

        verify(paymentRepository, never()).findByCustomer_CustomerId(any());
    }
}