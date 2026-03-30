package com.filmrental.service;

public interface InventoryService {
    InventoryResponse getInventoryById(Integer inventoryId);
    List<InventoryResponse> getInventoryByStoreId(Integer storeId);
    List<InventoryResponse> getInventoryByFilmId(Integer filmId);
}
