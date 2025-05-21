package com.delivery.delivery_api.dto;

import com.delivery.delivery_api.model.Order;
import lombok.Data;

@Data
public class OrderResponseDTO {

    private Long id;
    private String customerName;
    private Order.OrderStatus status;

}
