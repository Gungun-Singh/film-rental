package com.filmrental.service.impl;

import com.filmrental.dto.request.LoginRequest;
import com.filmrental.dto.response.AdminProfileResponse;
import com.filmrental.dto.response.AuthResponse;
import com.filmrental.entity.UserAccount;
import com.filmrental.exception.BadRequestException;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.repository.UserAccountRepository;
import com.filmrental.security.CustomUserPrincipal;
import com.filmrental.security.JwtUtil;
import com.filmrental.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserAccountRepository userAccountRepository;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();
        if (!"ADMIN".equalsIgnoreCase(principal.getRole())) {
            throw new BadCredentialsException("Only admin accounts can log in");
        }

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
    public AdminProfileResponse getAdminProfile(CustomUserPrincipal principal) {
        if (!"ADMIN".equalsIgnoreCase(principal.getRole())) {
            throw new BadRequestException("Only admin accounts can access the admin profile");
        }

        return AdminProfileResponse.builder()
                .userId(principal.getUserId())
                .username(principal.getUsername())
                .role(principal.getRole())
                .status(principal.getStatus())
                .lastLoginAt(userAccountRepository.findById(principal.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User account not found"))
                        .getLoginDate())
                .build();
    }
}
