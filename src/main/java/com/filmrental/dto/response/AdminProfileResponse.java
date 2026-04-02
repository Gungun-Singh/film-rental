package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminProfileResponse {
    private Integer userId;
    private Integer staffId;
    private String username;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private Integer storeId;
    private Boolean active;
    private String status;
}
