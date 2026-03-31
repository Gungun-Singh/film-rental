package com.filmrental.service;

import com.filmrental.dto.response.CustomerResponse;
import com.filmrental.dto.response.RentalResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomerById(Integer id);
    List<RentalResponse> getCustomerRentals(Integer customerId);

}