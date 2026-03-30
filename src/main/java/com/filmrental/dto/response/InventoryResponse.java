package com.filmrental.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponse {
    private Integer inventoryId;
    private Integer filmId;
    private String filmTitle;
    private Integer storeId;
    private boolean available;
}

