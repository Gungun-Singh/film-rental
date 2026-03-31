package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {
    private Integer storeId;
    private String address;
    private String city;
    private String country;
    private String managerFirstName;
    private String managerLastName;
}