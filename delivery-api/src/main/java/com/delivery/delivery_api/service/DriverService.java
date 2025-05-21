package com.delivery.delivery_api.service;

import com.delivery.delivery_api.dto.DriverDTO;
import com.delivery.delivery_api.model.Driver;
import com.delivery.delivery_api.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public  DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    private DriverDTO mapToDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setVehicleType(driver.getVehicleType());
        dto.setAvailable(driver.isAvailable());
        return dto;
    }


    //get all available drivers
    public List<DriverDTO> getAllAvailableDrivers() {
        return driverRepository.findAllByAvailableTrue()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    //update driver availability
    public void updateDriverAvailability(Long id, boolean available){
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setAvailable(available);
        driverRepository.save(driver);
    }

    //get driver by id;
    public Optional<DriverDTO> getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(this::mapToDTO);
    }

    //geta all drivers
    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }



}
