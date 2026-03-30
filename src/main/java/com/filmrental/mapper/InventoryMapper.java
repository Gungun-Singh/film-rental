package com.filmrental.mapper;

import com.filmrental.entity.Inventory;
import com.filmrental.dto.response.InventoryResponse;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public InventoryResponse toResponse(Inventory inv, boolean available) {
        return InventoryResponse.builder()
                .inventoryId(inv.getInventoryId())
                .filmId(inv.getFilm() != null ? inv.getFilm().getFilmId() : null)
                .filmTitle(inv.getFilm() != null ? inv.getFilm().getTitle() : null)
                .storeId(inv.getStore() != null ? inv.getStore().getStoreId() : null)
                .available(available).build();
    }
}