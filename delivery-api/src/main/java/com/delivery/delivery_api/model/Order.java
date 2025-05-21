package com.delivery.delivery_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String address;

    @ElementCollection
    private List<String> items;


    public enum OrderStatus {
        PENDING,
        ASSIGNED,
        IN_PROGRESS, SHIPPED, DELIVERED, COMPLETED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;


    public Order(String customerName, String address, List<String> items, OrderStatus status){
        this.customerName = customerName;
        this.address = address;
        this.items = items;
        this.status = status;
    }

}
