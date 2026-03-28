package com.filmrental.service;

import com.filmrental.dto.response.InventoryResponse;
import com.filmrental.dto.response.StoreResponse;

import java.util.List;

public interface StoreService {
    StoreResponse getStoreById(Integer storeId);
    List<InventoryResponse> getInventoryByStoreId(Integer storeId);
}