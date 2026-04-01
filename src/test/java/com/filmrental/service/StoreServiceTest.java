package com.filmrental.service;

import com.filmrental.entity.*;
import com.filmrental.dto.response.StoreResponse;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.repository.StoreRepository;
import com.filmrental.service.impl.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock private StoreRepository storeRepository;
    @InjectMocks private StoreServiceImpl storeService;

    private Store store;

    @BeforeEach
    void setUp() {
        Country country = new Country();
        country.setCountryId(1);
        country.setCountry("Canada");

        City city = new City();
        city.setCityId(1);
        city.setCity("Lethbridge");
        city.setCountry(country);

        Address address = new Address();
        address.setAddressId(1);
        address.setAddress("47 MySakila Drive");
        address.setCity(city);

        Staff staff = new Staff();
        staff.setStaffId(1);
        staff.setFirstName("Mike");
        staff.setLastName("Hillyer");

        store = new Store();
        store.setStoreId(1);
        store.setManagerStaff(staff);
        store.setAddress(address);
    }

    // Positive test
    // store exists → should return correct response
    @Test
    void getStoreById_storeExists_returnsStoreResponse() {
        // Arrange
        when(storeRepository.findById(1))
                .thenReturn(Optional.of(store));

        // Act
        StoreResponse result = storeService.getStoreById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1,                    result.getStoreId());
        assertEquals("47 MySakila Drive",  result.getAddress());
        assertEquals("Lethbridge",         result.getCity());
        assertEquals("Canada",             result.getCountry());
        assertEquals("Mike",               result.getManagerFirstName());
        assertEquals("Hillyer",            result.getManagerLastName());
        verify(storeRepository, times(1)).findById(1);
    }

    // Negative test
    // store does not exist → should throw 404
    @Test
    void getStoreById_storeNotFound_throwsResourceNotFoundException() {
        // Arrange
        when(storeRepository.findById(99))
                .thenReturn(Optional.empty());

        // Act + Assert
        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> storeService.getStoreById(99));

        assertEquals("Store not found with id: 99", ex.getMessage());
        verify(storeRepository, times(1)).findById(99);
    }
}