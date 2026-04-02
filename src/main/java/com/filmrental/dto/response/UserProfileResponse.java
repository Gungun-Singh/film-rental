package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private Integer userId;
    private Integer customerId;
    private String username;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private Integer active;
    private Integer storeId;
    private String status;
}
