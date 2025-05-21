package com.delivery.delivery_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private String customerName;
    private String address;
    private List<String> items;

}
