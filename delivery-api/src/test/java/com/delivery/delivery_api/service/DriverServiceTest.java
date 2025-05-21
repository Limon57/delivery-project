package com.delivery.delivery_api.service;

import com.delivery.delivery_api.dto.DriverDTO;
import com.delivery.delivery_api.model.Driver;
import com.delivery.delivery_api.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest {

    private DriverRepository driverRepository;
    private DriverService driverService;

    @BeforeEach
    void setUp() {
        driverRepository = mock(DriverRepository.class);
        driverService = new DriverService(driverRepository);
    }

    @Test
    void testGetAllAvailableDrivers() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setName("Alex");
        driver.setVehicleType(Driver.Vehicles.BIKE); // assuming enum has BIKE
        driver.setAvailable(true);

        when(driverRepository.findAllByAvailableTrue()).thenReturn(List.of(driver));

        List<DriverDTO> result = driverService.getAllAvailableDrivers();

        assertEquals(1, result.size());
        assertEquals("Alex", result.get(0).getName());
        assertTrue(result.get(0).isAvailable());
    }

    @Test
    void testUpdateDriverAvailability() {
        Driver driver = new Driver();
        driver.setId(2L);
        driver.setAvailable(false);

        when(driverRepository.findById(2L)).thenReturn(Optional.of(driver));

        driverService.updateDriverAvailability(2L, true);

        assertTrue(driver.isAvailable());
        verify(driverRepository).save(driver);
    }

    @Test
    void testGetDriverById() {
        Driver driver = new Driver();
        driver.setId(3L);
        driver.setName("Jess");
        driver.setVehicleType(Driver.Vehicles.BIKE); // assuming enum has BIKE
        driver.setAvailable(true);

        when(driverRepository.findById(3L)).thenReturn(Optional.of(driver));

        Optional<DriverDTO> result = driverService.getDriverById(3L);

        assertTrue(result.isPresent());
        assertEquals("Jess", result.get().getName());
    }

    @Test
    void testGetAllDrivers() {
        Driver driver = new Driver();
        driver.setId(4L);
        driver.setName("Jordan");

        when(driverRepository.findAll()).thenReturn(List.of(driver));

        List<DriverDTO> result = driverService.getAllDrivers();

        assertEquals(1, result.size());
        assertEquals("Jordan", result.get(0).getName());
    }
}
