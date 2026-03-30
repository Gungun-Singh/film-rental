package com.filmrental.service;

import com.filmrental.dto.response.StoreResponse;
import com.filmrental.entity.Store;
import com.filmrental.repository.StoreRepository;

import java.util.List;

public interface StoreService {
    StoreResponse getStoreById(Integer storeId);

}