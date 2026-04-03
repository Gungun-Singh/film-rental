package com.filmrental.service.impl;

import com.filmrental.dto.response.RentalResponse;
import com.filmrental.mapper.RentalMapper;
import com.filmrental.repository.RentalRepository;
import com.filmrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    @Override @Transactional(readOnly=true)
    public List<RentalResponse> getRentalsByCustomerId(Integer customerId) {
        return rentalRepository.findByCustomer_CustomerId(customerId)
                .stream().map(rentalMapper::toResponse).collect(Collectors.toList());
    }
}
