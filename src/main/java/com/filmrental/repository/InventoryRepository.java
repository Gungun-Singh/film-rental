package com.filmrental.repository;

import com.filmrental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByFilm_FilmId(Integer filmId);

    // Returns inventory items for a film that have no open rental (return_date IS NULL)
    @Query("SELECT i FROM Inventory i WHERE i.film.filmId = :filmId " +
            "AND i.inventoryId NOT IN (" +
            "  SELECT r.inventory.inventoryId FROM Rental r WHERE r.returnDate IS NULL" +
            ")")
    List<Inventory> findAvailableInventoryByFilmId(@Param("filmId") Integer filmId);
}