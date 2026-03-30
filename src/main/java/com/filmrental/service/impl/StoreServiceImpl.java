package com.filmrental.service.impl;

import com.filmrental.entity.Store;
import com.filmrental.dto.response.StoreResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.repository.StoreRepository;
import com.filmrental.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.filmrental.entity.Staff;
import com.filmrental.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getStoreById(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + storeId));

        Staff manager = staffRepository.findById(store.getManagerStaffId())
                .orElse(null);

        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .addressId(store.getAddressId())
                .managerFirstName(manager != null ? manager.getFirstName() : null)
                .managerLastName(manager != null ? manager.getLastName() : null)
                .build();
    }
}