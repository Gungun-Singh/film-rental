package com.filmrental.security;

import com.filmrental.entity.UserAccount;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserPrincipal implements UserDetails {

    private final Integer userId;
    private final Integer customerId;
    private final Integer staffId;
    private final String username;
    private final String password;
    private final String role;
    private final String status;

    public CustomUserPrincipal(UserAccount userAccount) {
        this.userId = userAccount.getUserId();
        this.customerId = userAccount.getCustomerId();
        this.staffId = userAccount.getStaffId();
        this.username = userAccount.getUsername();
        this.password = userAccount.getPassword();
        this.role = userAccount.getRole();
        this.status = userAccount.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == null || !"INACTIVE".equalsIgnoreCase(status);
    }
}
