package com.filmrental.mapper;

import com.filmrental.entity.Customer;
import com.filmrental.dto.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerResponse toResponse(Customer c) {
        return CustomerResponse.builder()
                .customerId(c.getCustomerId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .email(c.getEmail())
                .active(c.getActive())
                .storeId(c.getStore() != null ? String.valueOf(c.getStore().getStoreId()) : "N/A")
                .build();
    }
}