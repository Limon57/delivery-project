package com.delivery.delivery_api.service;

import com.delivery.delivery_api.dto.OrderRequestDTO;
import com.delivery.delivery_api.dto.OrderResponseDTO;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private  final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    //create order
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO){

        // Map DTO
        Order order = new Order();
        order.setCustomerName(orderRequestDTO.getCustomerName());
        order.setAddress(orderRequestDTO.getAddress());
        order.setItems(orderRequestDTO.getItems());
        order.setStatus(Order.OrderStatus.PENDING);

        //save order
        Order savedOrder = orderRepository.save(order);

        //map response
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(savedOrder.getId());
        orderResponseDTO.setCustomerName(savedOrder.getCustomerName());
        orderResponseDTO.setStatus(savedOrder.getStatus());

        return orderResponseDTO;
    }


    //get all orders
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    //get Order by id
    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    //update order status
    public void updateOrderStatus(Long id, Order.OrderStatus status){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);
    }

    public Order.OrderStatus getOrderStatus(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"))
                .getStatus();
    }

}
