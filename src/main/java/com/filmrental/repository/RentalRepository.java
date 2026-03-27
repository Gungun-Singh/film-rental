package com.filmrental.repository;

import com.filmrental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findByCustomer_CustomerId(Integer customerId);

    // All open rentals (not yet returned)
    @Query("SELECT r FROM Rental r WHERE r.returnDate IS NULL")
    List<Rental> findAllOpenRentals();

    // All rentals for a specific inventory item
    List<Rental> findByInventory_InventoryId(Integer inventoryId);
}