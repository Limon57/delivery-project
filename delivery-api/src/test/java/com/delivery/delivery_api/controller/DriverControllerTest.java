package com.delivery.delivery_api.controller;

import com.delivery.delivery_api.dto.DriverDTO;
import com.delivery.delivery_api.model.Driver;
import com.delivery.delivery_api.service.DriverService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DriverController.class)
public class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")

    @MockBean
    private DriverService driverService;

    @Test
    public void testGetAllAvailableDrivers() throws Exception {
        DriverDTO driver = new DriverDTO();
        driver.setId(1L);
        driver.setName("Alex");
        driver.setVehicleType(Driver.Vehicles.BIKE);
        driver.setAvailable(true);

        when(driverService.getAllAvailableDrivers()).thenReturn(List.of(driver));

        mockMvc.perform(get("/api/driver/get-available-drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Alex"));
    }

    @Test
    public void testGetDriverById() throws Exception {
        DriverDTO driver = new DriverDTO();
        driver.setId(2L);
        driver.setName("Taylor");
        driver.setVehicleType(Driver.Vehicles.CAR);
        driver.setAvailable(false);

        when(driverService.getDriverById(2L)).thenReturn(Optional.of(driver));

        mockMvc.perform(get("/api/driver/get-driver")
                        .param("id", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Taylor"))
                .andExpect(jsonPath("$.vehicleType").value("CAR"))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    public void testUpdateDriverAvailability() throws Exception {
        doNothing().when(driverService).updateDriverAvailability(3L, true);

        mockMvc.perform(put("/api/driver/update-availability")
                        .param("id", "3")
                        .param("available", "true"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllDrivers() throws Exception {
        DriverDTO d1 = new DriverDTO();
        d1.setId(1L);
        d1.setName("Alex");

        DriverDTO d2 = new DriverDTO();
        d2.setId(2L);
        d2.setName("Taylor");

        when(driverService.getAllDrivers()).thenReturn(List.of(d1, d2));

        mockMvc.perform(get("/api/driver/get-all-drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
