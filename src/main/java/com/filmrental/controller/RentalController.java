package com.filmrental.controller;

import com.filmrental.dto.response.RentalResponse;
import com.filmrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/v1/rentals") @RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    // GET /rentals/customer/{customer_id}
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RentalResponse>> getRentalsByCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(rentalService.getRentalsByCustomerId(customerId));
    }
}
