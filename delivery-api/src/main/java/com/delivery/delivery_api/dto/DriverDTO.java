package com.delivery.delivery_api.dto;

import com.delivery.delivery_api.model.Driver;
import com.delivery.delivery_api.model.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverDTO {

    private Long id;
    private String name;
    private Driver.Vehicles vehicleType;
    private boolean available;
}
