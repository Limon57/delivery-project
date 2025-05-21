package com.delivery.delivery_api.controller;

import com.delivery.delivery_api.dto.DeliveryRequestDTO;
import com.delivery.delivery_api.dto.DeliveryResponseDTO;
import com.delivery.delivery_api.model.Delivery;
import com.delivery.delivery_api.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DeliveryController.class)
public class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryService deliveryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateDelivery() throws Exception {
        DeliveryRequestDTO requestDTO = new DeliveryRequestDTO();
        requestDTO.setOrderId(1L);
        requestDTO.setDriverId(2L);

        DeliveryResponseDTO responseDTO = new DeliveryResponseDTO();
        responseDTO.setId(100L);
        responseDTO.setOrderId(1L);
        responseDTO.setDriverId(2L);
        responseDTO.setStatus(Delivery.StatusValues.CREATED);
        responseDTO.setStartTime(LocalDateTime.now());

        when(deliveryService.createDelivery(Mockito.any(DeliveryRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/delivery/create-delivery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.driverId").value(2L))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    public void testGetDeliveryById() throws Exception {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setStatus(Delivery.StatusValues.IN_TRANSIT);

        when(deliveryService.getDeliveryById(1L)).thenReturn(Optional.of(delivery));

        mockMvc.perform(get("/api/delivery/get-delivery-by-id")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("IN_TRANSIT"));
    }

    @Test
    public void testGetAllDeliveries() throws Exception {
        Delivery d1 = new Delivery();
        d1.setId(1L);
        d1.setStatus(Delivery.StatusValues.CREATED);

        Delivery d2 = new Delivery();
        d2.setId(2L);
        d2.setStatus(Delivery.StatusValues.DELIVERED);

        when(deliveryService.getAllDeliveries()).thenReturn(List.of(d1, d2));

        mockMvc.perform(get("/api/delivery/get-all-deliveries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testUpdateDeliveryStatus() throws Exception {
        doNothing().when(deliveryService).updateDelivery(1L, Delivery.StatusValues.DELIVERED);

        mockMvc.perform(put("/api/delivery/update-status")
                        .param("id", "1")
                        .param("status", "DELIVERED"))
                .andExpect(status().isOk());
    }
}
