package com.delivery.delivery_api.controller;

import com.delivery.delivery_api.dto.DeliveryRequestDTO;
import com.delivery.delivery_api.dto.DeliveryResponseDTO;
import com.delivery.delivery_api.model.Delivery;
import com.delivery.delivery_api.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    //create delivery
    @PostMapping("create-delivery")
    public DeliveryResponseDTO createDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO){
        return deliveryService.createDelivery(deliveryRequestDTO);
    }

    //get delivery by id
    @GetMapping("get-delivery-by-id")
    public Optional<Delivery> getDeliveryById(@RequestParam Long id){
        return deliveryService.getDeliveryById(id);
    }

    //get all deliveries
    @GetMapping("get-all-deliveries")
    public List<Delivery> getAllDeliveries(){
        return deliveryService.getAllDeliveries();
    }

    //update delivery status
    @PutMapping("update-status")
    public void updateDeliveryStatus(@RequestParam Long id, @RequestParam Delivery.StatusValues status){
        deliveryService.updateDelivery(id, status);
    }

}
