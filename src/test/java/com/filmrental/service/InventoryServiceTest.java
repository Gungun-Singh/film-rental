package com.filmrental.service;

import com.filmrental.entity.Inventory;
import com.filmrental.dto.response.InventoryResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.InventoryMapper;
import com.filmrental.repository.InventoryRepository;
import com.filmrental.repository.RentalRepository;
import com.filmrental.repository.StoreRepository;
import com.filmrental.service.impl.InventoryServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class InventoryServiceTest {

        @Mock
        private InventoryRepository inventoryRepository;

        @Mock
        private RentalRepository rentalRepository;

        @Mock
        private StoreRepository storeRepository;

        @Mock
        private InventoryMapper inventoryMapper;

        @InjectMocks
        private InventoryServiceImpl inventoryService;


        @Test
        void getInventoryById_success() {

            Inventory inventory = new Inventory();
            inventory.setInventoryId(1);

            InventoryResponse response = InventoryResponse.builder()
                    .inventoryId(1)
                    .available(true)
                    .build();

            when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventory));
            when(rentalRepository.existsByInventory_InventoryIdAndReturnDateIsNull(1)).thenReturn(false);
            when(inventoryMapper.toResponse(inventory,true)).thenReturn(response);

            InventoryResponse result = inventoryService.getInventoryById(1);

            assertNotNull(result);
            assertEquals(1,result.getInventoryId());
        }
        @Test
        void getInventoryById_notFound() {

            when(inventoryRepository.findById(10)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class,
                    () -> inventoryService.getInventoryById(10));
        }
        @Test
        void getInventoryByStoreId_success() {

            Inventory inv = new Inventory();
            inv.setInventoryId(1);

            when(storeRepository.existsById(1)).thenReturn(true);
            when(inventoryRepository.findByStore_StoreId(1)).thenReturn(List.of(inv));

            when(rentalRepository.existsByInventory_InventoryIdAndReturnDateIsNull(1))
                    .thenReturn(false);

            when(inventoryMapper.toResponse(any(),anyBoolean()))
                    .thenReturn(InventoryResponse.builder().inventoryId(1).build());

            List<InventoryResponse> result = inventoryService.getInventoryByStoreId(1);

            assertEquals(1,result.size());
        }
        
        @Test
        void getInventoryByStoreId_storeNotFound() {

            when(storeRepository.existsById(5)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class,
                    () -> inventoryService.getInventoryByStoreId(5));
        }


    }

