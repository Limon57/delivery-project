package com.delivery.delivery_api.controller;

import com.delivery.delivery_api.dto.OrderRequestDTO;
import com.delivery.delivery_api.dto.OrderResponseDTO;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    //create order
    @PostMapping("/create-order")
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.createOrder(orderRequestDTO);
    }

    //get order by id
    @GetMapping("/get-order")
    public Optional<Order>  getOrderById(@RequestParam Long id){
        return orderService.getOrderById(id);
    }

    //get all orders
    @GetMapping("/get-all-orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    //get order status
    @GetMapping("/status")
    public Order.OrderStatus getOrderStatus(@RequestParam Long id) {
        return orderService.getOrderStatus(id);
    }

    //update order status
    @PutMapping("/update-status")
    public void updateOrderStatus(@RequestParam Long id, @RequestParam Order.OrderStatus status) {
        orderService.updateOrderStatus(id, status);
    }
}
