package com.delivery.delivery_api.dto;

import lombok.Data;

@Data
public class DeliveryRequestDTO {

    private Long orderId;
    private Long driverId;
}
