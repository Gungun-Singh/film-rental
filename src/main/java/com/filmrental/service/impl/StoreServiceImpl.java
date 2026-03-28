package com.filmrental.service.impl;

import com.filmrental.entity.Inventory;
import com.filmrental.entity.Store;
import com.filmrental.dto.response.InventoryResponse;
import com.filmrental.dto.response.StoreResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.InventoryMapper;
import com.filmrental.repository.InventoryRepository;
import com.filmrental.repository.RentalRepository;
import com.filmrental.repository.StoreRepository;
import com.filmrental.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final InventoryRepository inventoryRepository;
    private final RentalRepository rentalRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getStoreById(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + storeId));

        String managerFirst = null;
        String managerLast  = null;
        if (store.getManagerStaff() != null) {
            managerFirst = store.getManagerStaff().getFirstName();
            managerLast  = store.getManagerStaff().getLastName();
        }

        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .addressId(store.getAddressId())
                .managerFirstName(managerFirst)
                .managerLastName(managerLast)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> getInventoryByStoreId(Integer storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new ResourceNotFoundException(
                    "Store not found with id: " + storeId);
        }
        List<Inventory> inventoryList =
                inventoryRepository.findByStore_StoreId(storeId);
        return inventoryList.stream()
                .map(inv -> {
                    boolean available = !rentalRepository
                            .existsByInventory_InventoryIdAndReturnDateIsNull(
                                    inv.getInventoryId());
                    return inventoryMapper.toResponse(inv, available);
                })
                .collect(Collectors.toList());
    }
}