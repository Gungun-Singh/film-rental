package com.filmrental.service;

import com.filmrental.dto.response.StoreResponse;

public interface StoreService {
    StoreResponse getStoreById(Integer storeId);
    StoreResponse getManagerById(Integer managerStaffId);
}