package com.filmrental.service;

import com.filmrental.dto.request.LoginRequest;
import com.filmrental.dto.response.AdminProfileResponse;
import com.filmrental.dto.response.AuthResponse;
import com.filmrental.dto.response.UserProfileResponse;
import com.filmrental.security.CustomUserPrincipal;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    UserProfileResponse getUserProfile(CustomUserPrincipal principal);

    AdminProfileResponse getAdminProfile(CustomUserPrincipal principal);
}
