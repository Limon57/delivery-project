package com.delivery.delivery_api.dto;

import com.delivery.delivery_api.model.Delivery;
import com.delivery.delivery_api.model.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryEventDTO {

    private Long deliveryId;
    private Long orderId;
    private LocalDateTime timestamp;
    private Delivery.StatusValues status;


}
