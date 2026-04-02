package com.filmrental.service.impl;

import com.filmrental.dto.request.LoginRequest;
import com.filmrental.dto.response.AdminProfileResponse;
import com.filmrental.dto.response.AuthResponse;
import com.filmrental.dto.response.UserProfileResponse;
import com.filmrental.entity.Customer;
import com.filmrental.entity.Staff;
import com.filmrental.entity.UserAccount;
import com.filmrental.exception.BadRequestException;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.repository.CustomerRepository;
import com.filmrental.repository.StaffRepository;
import com.filmrental.repository.UserAccountRepository;
import com.filmrental.security.CustomUserPrincipal;
import com.filmrental.security.JwtUtil;
import com.filmrental.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserAccountRepository userAccountRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        UserAccount userAccount = userAccountRepository.findById(principal.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User account not found"));

        userAccount.setLoginDate(LocalDateTime.now());
        userAccountRepository.save(userAccount);

        return AuthResponse.builder()
                .token(jwtUtil.generateToken(principal))
                .tokenType("Bearer")
                .expiresIn(jwtUtil.getExpiration())
                .userId(principal.getUserId())
                .username(principal.getUsername())
                .role(principal.getRole())
                .build();
    }

    @Override
    public UserProfileResponse getUserProfile(CustomUserPrincipal principal) {
        if (!"USER".equalsIgnoreCase(principal.getRole())) {
            throw new BadRequestException("Only user accounts can access the user profile");
        }

        Integer customerId = principal.getCustomerId();
        if (customerId == null) {
            throw new BadRequestException("User account is not linked to a customer profile");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        return UserProfileResponse.builder()
                .userId(principal.getUserId())
                .customerId(customer.getCustomerId())
                .username(principal.getUsername())
                .role(principal.getRole())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .active(customer.getActive())
                .storeId(customer.getStore() != null ? customer.getStore().getStoreId() : null)
                .status(principal.getStatus())
                .build();
    }

    @Override
    public AdminProfileResponse getAdminProfile(CustomUserPrincipal principal) {
        if (!"ADMIN".equalsIgnoreCase(principal.getRole())) {
            throw new BadRequestException("Only admin accounts can access the admin profile");
        }

        Integer staffId = principal.getStaffId();
        Staff staff = staffId != null
                ? staffRepository.findById(staffId)
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId))
                : staffRepository.findByUsername(principal.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found for username: " + principal.getUsername()));

        return AdminProfileResponse.builder()
                .userId(principal.getUserId())
                .staffId(staff.getStaffId())
                .username(principal.getUsername())
                .role(principal.getRole())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email(staff.getEmail())
                .storeId(staff.getStoreId())
                .active(staff.getActive())
                .status(principal.getStatus())
                .build();
    }
}
