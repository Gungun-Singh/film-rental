package com.filmrental.service.impl;

import com.filmrental.entity.Inventory;
import com.filmrental.dto.response.InventoryResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.InventoryMapper;
import com.filmrental.repository.InventoryRepository;
import com.filmrental.repository.RentalRepository;
import com.filmrental.repository.StoreRepository;
import com.filmrental.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final RentalRepository rentalRepository;
    private final StoreRepository storeRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse getInventoryById(Integer inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory not found with id: " + inventoryId));
        boolean available = !rentalRepository
                .existsByInventory_InventoryIdAndReturnDateIsNull(inventoryId);
        return inventoryMapper.toResponse(inventory, available);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> getInventoryByStoreId(Integer storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new ResourceNotFoundException(
                    "Store not found with id: " + storeId);
        }
        return inventoryRepository.findByStore_StoreId(storeId)
                .stream()
                .map(inv -> {
                    boolean available = !rentalRepository
                            .existsByInventory_InventoryIdAndReturnDateIsNull(
                                    inv.getInventoryId());
                    return inventoryMapper.toResponse(inv, available);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> getInventoryByFilmId(Integer filmId) {
        List<Inventory> list = inventoryRepository.findByFilm_FilmId(filmId);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No inventory found for film id: " + filmId);
        }
        return list.stream()
                .map(inv -> {
                    boolean available = !rentalRepository
                            .existsByInventory_InventoryIdAndReturnDateIsNull(
                                    inv.getInventoryId());
                    return inventoryMapper.toResponse(inv, available);
                })
                .collect(Collectors.toList());
    }
}
