package com.delivery.delivery_api;

import com.delivery.delivery_api.model.Driver;
import com.delivery.delivery_api.model.Delivery;
import com.delivery.delivery_api.model.Order;
import com.delivery.delivery_api.model.Driver.Vehicles;
import com.delivery.delivery_api.model.Delivery.StatusValues;
import com.delivery.delivery_api.repository.DriverRepository;
import com.delivery.delivery_api.repository.DeliveryRepository;
import com.delivery.delivery_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataTestRunner implements CommandLineRunner {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public void run(String... args) {
        // Create and save driver
        Driver driver = new Driver("Mario", Vehicles.TRUCK, true);
        driver = driverRepository.save(driver);
        System.out.println("Saved Driver: id=" + driver.getId() + ", name=" + driver.getName());

        // Create and save order
        List<String> items = List.of("1-Up Mushroom", "Fire Flower");
        Order order = new Order("Luigi", "123 Mushroom Lane", items, Order.OrderStatus.PENDING);
        order = orderRepository.save(order);
        System.out.println("Saved Order: id=" + order.getId() + ", customer=" + order.getCustomerName());

        // Create and save delivery
        Delivery delivery = new Delivery(LocalDateTime.now(), null, StatusValues.CREATED, order, driver);
        delivery = deliveryRepository.save(delivery);
        System.out.println("Saved Delivery: id=" + delivery.getId() + ", status=" + delivery.getStatus());

        // Fetch and print everything
        System.out.println("\nAll Drivers:");
        driverRepository.findAll().forEach(d ->
                System.out.println("id=" + d.getId() + ", name=" + d.getName() + ", vehicle=" + d.getVehicleType())
        );

        System.out.println("\nAll Orders:");
        orderRepository.findAll().forEach(o ->
                System.out.println("id=" + o.getId() + ", customer=" + o.getCustomerName() + ", address=" + o.getAddress())
        );

        System.out.println("\nAll Deliveries:");
        deliveryRepository.findAll().forEach(del ->
                System.out.println("id=" + del.getId() + ", status=" + del.getStatus() + ", driver=" + del.getDriver().getName())
        );
    }
}
