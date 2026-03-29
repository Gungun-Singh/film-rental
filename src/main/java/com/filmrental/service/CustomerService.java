package com.filmrental.service;

import com.filmrental.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getCustomerById(Integer id);
}