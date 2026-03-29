package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer active;
    private String storeId;
}