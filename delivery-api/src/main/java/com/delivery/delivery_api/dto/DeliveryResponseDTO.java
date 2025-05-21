package com.delivery.delivery_api.dto;

import com.delivery.delivery_api.model.Delivery;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryResponseDTO {

    private Long id;
    private Long orderId;
    private Long driverId;
    private Delivery.StatusValues status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
