package com.delivery.delivery_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public enum StatusValues {
        CREATED,
        IN_TRANSIT,
        DELIVERED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusValues status;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    public Delivery(LocalDateTime startTime, LocalDateTime endTime, StatusValues status, Order order, Driver driver) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.order = order;
        this.driver = driver;
    }
}

