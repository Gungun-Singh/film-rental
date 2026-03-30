package com.filmrental.controller;


import com.filmrental.dto.response.StoreResponse;
import com.filmrental.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // GET /stores/{store_id}
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable Integer storeId) {
        return ResponseEntity.ok(storeService.getStoreById(storeId));
    }

}