package com.delivery.delivery_api.service;

import com.delivery.delivery_api.kafka.DeliveryStatusProducer;
import com.delivery.delivery_api.model.Delivery;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.model.Driver;
import com.delivery.delivery_api.repository.DeliveryRepository;
import com.delivery.delivery_api.repository.DriverRepository;
import com.delivery.delivery_api.repository.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryServiceTest {

    private DeliveryRepository deliveryRepository;
    private OrderRepository orderRepository;
    private DriverRepository driverRepository;
    private DeliveryStatusProducer deliveryStatusProducer;

    private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        deliveryRepository = mock(DeliveryRepository.class);
        orderRepository = mock(OrderRepository.class);
        driverRepository = mock(DriverRepository.class);
        deliveryStatusProducer = mock(DeliveryStatusProducer.class);

        deliveryService = new DeliveryService(
                deliveryRepository,
                orderRepository,
                driverRepository,
                deliveryStatusProducer
        );
    }

    @Test
    void testGetDeliveryById_whenFound() {
        Delivery delivery = new Delivery();
        delivery.setId(1L);

        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));

        Optional<Delivery> result = deliveryService.getDeliveryById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(deliveryRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDeliveryById_whenNotFound() {
        when(deliveryRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Delivery> result = deliveryService.getDeliveryById(2L);

        assertFalse(result.isPresent());
        verify(deliveryRepository, times(1)).findById(2L);
    }
}
