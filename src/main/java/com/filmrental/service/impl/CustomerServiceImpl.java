package com.filmrental.service.impl;

import com.filmrental.entity.Customer;
import com.filmrental.dto.response.CustomerResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.CustomerMapper;
import com.filmrental.repository.CustomerRepository;
import com.filmrental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly=true)
    public CustomerResponse getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return customerMapper.toResponse(customer);
    }
}