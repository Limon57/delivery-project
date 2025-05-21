package com.delivery.delivery_tracker.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryEventDTO {

    private Long deliveryId;
    private Long orderId;
    private Status status;
    private LocalDateTime timestamp;

    public enum Status {
        CREATED,
        IN_TRANSIT,
        DELIVERED
    }
}
