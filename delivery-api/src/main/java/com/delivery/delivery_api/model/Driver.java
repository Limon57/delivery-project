package com.delivery.delivery_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public static enum Vehicles {
        CAR,
        TRUCK,
        BIKE,
        VAN
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Vehicles vehicleType;

    @Column(nullable = false)
    private boolean available;

    public Driver(String name, Vehicles vehicleType, boolean available){
        this.name = name;
        this.vehicleType = vehicleType;
        this.available = available;
    }

}
