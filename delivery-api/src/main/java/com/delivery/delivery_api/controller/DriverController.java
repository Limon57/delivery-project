package com.delivery.delivery_api.controller;

import com.delivery.delivery_api.dto.DriverDTO;
import com.delivery.delivery_api.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    //get all available drivers
    @GetMapping("get-available-drivers")
    public List<DriverDTO> getAllAvailableDrivers(){
        return driverService.getAllAvailableDrivers();
    }

    @GetMapping("get-driver")
    public DriverDTO getDriverById(@RequestParam Long id) {
        return driverService.getDriverById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }



    @PutMapping("update-availability")
    //update driver availability
    public void updateDriverAvailability(@RequestParam Long id, boolean available){
        driverService.updateDriverAvailability(id, available);
    }

    //get all drivers
    @GetMapping("get-all-drivers")
    public List<DriverDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }



}
