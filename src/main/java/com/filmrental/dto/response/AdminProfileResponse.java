package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminProfileResponse {
    private Integer userId;
    private String username;
    private String role;
    private String status;
    private LocalDateTime lastLoginAt;
}
