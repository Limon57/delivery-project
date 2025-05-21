package com.delivery.delivery_api.service;

import com.delivery.delivery_api.dto.OrderRequestDTO;
import com.delivery.delivery_api.dto.OrderResponseDTO;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void testCreateOrder() {
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setCustomerName("Sam");
        dto.setAddress("123 Street");
        dto.setItems(List.of("Shoes"));

        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("Sam");
        order.setStatus(Order.OrderStatus.PENDING);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponseDTO response = orderService.createOrder(dto);

        assertEquals("Sam", response.getCustomerName());
        assertEquals(Order.OrderStatus.PENDING, response.getStatus());
    }

    @Test
    void testGetAllOrders() {
        Order order = new Order();
        order.setId(2L);

        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> result = orderService.getAllOrders();

        assertEquals(1, result.size());
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(3L);

        when(orderRepository.findById(3L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(3L);

        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateOrderStatus() {
        Order order = new Order();
        order.setId(4L);
        order.setStatus(Order.OrderStatus.PENDING);

        when(orderRepository.findById(4L)).thenReturn(Optional.of(order));

        orderService.updateOrderStatus(4L, Order.OrderStatus.COMPLETED);

        assertEquals(Order.OrderStatus.COMPLETED, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void testGetOrderStatus() {
        Order order = new Order();
        order.setId(5L);
        order.setStatus(Order.OrderStatus.IN_PROGRESS);

        when(orderRepository.findById(5L)).thenReturn(Optional.of(order));

        Order.OrderStatus status = orderService.getOrderStatus(5L);

        assertEquals(Order.OrderStatus.IN_PROGRESS, status);
    }
}
