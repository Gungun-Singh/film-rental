package com.filmrental.service;

import com.filmrental.dto.response.RentalResponse;

import java.util.List;

public interface RentalService {
    List<RentalResponse> getRentalsByCustomerId(Integer customerId);
}
