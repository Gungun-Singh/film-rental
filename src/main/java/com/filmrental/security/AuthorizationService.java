package com.filmrental.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public void assertCustomerAccess(CustomUserPrincipal principal, Integer requestedCustomerId) {
        if (principal == null) {
            throw new AccessDeniedException("Authentication is required");
        }

        if (isAdmin(principal)) {
            return;
        }

        if (principal.getCustomerId() == null || !principal.getCustomerId().equals(requestedCustomerId)) {
            throw new AccessDeniedException("You can only access your own customer data");
        }
    }

    private boolean isAdmin(CustomUserPrincipal principal) {
        return "ADMIN".equalsIgnoreCase(principal.getRole());
    }
}
