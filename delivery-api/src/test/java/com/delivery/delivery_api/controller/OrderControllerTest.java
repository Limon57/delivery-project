package com.delivery.delivery_api.controller;

import com.delivery.delivery_api.dto.OrderRequestDTO;
import com.delivery.delivery_api.dto.OrderResponseDTO;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;



import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")

    @MockBean
    private OrderService orderService;

    @Test
    public void testCreateOrder() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerName("John Doe");
        request.setAddress("123 Main St");
        request.setItems(List.of("item1", "item2"));

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(1L);
        response.setCustomerName("John Doe");
        response.setStatus(Order.OrderStatus.PENDING);

        when(orderService.createOrder(Mockito.any())).thenReturn(response);

        String jsonRequest = """
            {
              "customerName": "John Doe",
              "address": "123 Main St",
              "items": ["item1", "item2"]
            }
        """;

        mockMvc.perform(post("/api/order/create-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setId(2L);
        order.setCustomerName("Jane");
        order.setStatus(Order.OrderStatus.SHIPPED);

        when(orderService.getOrderById(2L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/order/get-order").param("id", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.customerName").value("Jane"))
                .andExpect(jsonPath("$.status").value("SHIPPED"));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Order o1 = new Order();
        o1.setId(1L);
        o1.setCustomerName("John");

        Order o2 = new Order();
        o2.setId(2L);
        o2.setCustomerName("Jane");

        when(orderService.getAllOrders()).thenReturn(List.of(o1, o2));

        mockMvc.perform(get("/api/order/get-all-orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetOrderStatus() throws Exception {
        when(orderService.getOrderStatus(1L)).thenReturn(Order.OrderStatus.DELIVERED);

        mockMvc.perform(get("/api/order/status").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("\"DELIVERED\""));
    }

    @Test
    public void testUpdateOrderStatus() throws Exception {
        doNothing().when(orderService).updateOrderStatus(1L, Order.OrderStatus.SHIPPED);

        mockMvc.perform(put("/api/order/update-status")
                        .param("id", "1")
                        .param("status", "SHIPPED"))
                .andExpect(status().isOk());
    }
}
