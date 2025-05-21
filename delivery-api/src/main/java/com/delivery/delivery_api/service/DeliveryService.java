package com.delivery.delivery_api.service;

import com.delivery.delivery_api.dto.DeliveryEventDTO;
import com.delivery.delivery_api.dto.DeliveryRequestDTO;
import com.delivery.delivery_api.dto.DeliveryResponseDTO;
import com.delivery.delivery_api.kafka.DeliveryStatusProducer;
import com.delivery.delivery_api.model.Delivery;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.repository.DeliveryRepository;
import com.delivery.delivery_api.repository.DriverRepository;
import com.delivery.delivery_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.delivery_api.model.Driver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {


    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;

    DeliveryStatusProducer deliveryStatusProducer;



    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository, DriverRepository driverRepository, DeliveryStatusProducer deliveryStatusProducer){
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.deliveryStatusProducer = deliveryStatusProducer;
    }

    //create delivery
    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO deliveryRequestDTO){

        // fetch order and driver
        Order order = orderRepository.findById(deliveryRequestDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Driver driver = driverRepository.findById(deliveryRequestDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));


        // create and populate Delivery
        Delivery delivery = new Delivery();
        delivery.setDriver(driver);
        delivery.setOrder(order);

        delivery.setStartTime(LocalDateTime.now());
        delivery.setStatus(Delivery.StatusValues.CREATED);

        // save to DB
        Delivery saved = deliveryRepository.save(delivery);

        //send kafka event
        DeliveryEventDTO event = new DeliveryEventDTO();
        event.setDeliveryId(saved.getId());
        event.setOrderId(saved.getOrder().getId());
        event.setStatus(saved.getStatus());
        event.setTimestamp(LocalDateTime.now());

        deliveryStatusProducer.sendDeliveryStatus(event);


        // map to Response DTO
        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setOrderId(saved.getOrder().getId());
        responseDTO.setDriverId(saved.getDriver().getId());
        responseDTO.setStatus(saved.getStatus());
        responseDTO.setStartTime(saved.getStartTime());
        responseDTO.setEndTime(saved.getEndTime());

        return responseDTO;
    }


    //update delivery status
    public void updateDelivery(Long id, Delivery.StatusValues status){
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(status);
        deliveryRepository.save(delivery);

    }


    //get delivery by id
    public Optional<Delivery> getDeliveryById(Long id){
        return deliveryRepository.findById(id);
    }

    //get all deliveries
    public List<Delivery> getAllDeliveries(){
        return deliveryRepository.findAll();
    }


}
