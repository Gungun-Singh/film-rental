package com.filmrental.controller;

import com.filmrental.dto.response.CustomerResponse;
import com.filmrental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }
}