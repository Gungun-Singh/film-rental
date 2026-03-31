package com.filmrental.service.impl;

import com.filmrental.entity.Address;
import com.filmrental.entity.Store;
import com.filmrental.dto.response.StoreResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.repository.StoreRepository;
import com.filmrental.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private StoreResponse buildResponse(Store store) {
        Address addr = store.getAddress();

        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .address(addr != null
                        ? addr.getAddress() : null)
                .city(addr != null && addr.getCity() != null
                        ? addr.getCity().getCity() : null)
                .country(addr != null && addr.getCity() != null
                        && addr.getCity().getCountry() != null
                        ? addr.getCity().getCountry().getCountry() : null)
                .managerFirstName(store.getManagerStaff() != null
                        ? store.getManagerStaff().getFirstName() : null)
                .managerLastName(store.getManagerStaff() != null
                        ? store.getManagerStaff().getLastName() : null)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getStoreById(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + storeId));
        return buildResponse(store);
    }

    // add this method inside your existing StoreServiceImpl

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getManagerById(Integer managerStaffId) {
        Store store = storeRepository.findByManagerStaff_StaffId(managerStaffId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No store found for manager with id: " + managerStaffId));
        return buildResponse(store);
    }


}