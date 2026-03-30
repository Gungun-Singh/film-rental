package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {
    private Integer storeId;
    private Integer addressId;
    private String managerFirstName;
    private String managerLastName;
}