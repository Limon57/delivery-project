package com.delivery.delivery_tracker.consumer;

import com.delivery.delivery_tracker.dto.DeliveryEventDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusConsumer {

    @Value("${delivery.kafka.topic}")
    private String topic;

    @KafkaListener(topics = "${delivery.kafka.topic}", groupId = "delivery-tracker-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(DeliveryEventDTO event) {
        System.out.println("📦 Received Delivery Event:");
        System.out.println("➡ Delivery ID: " + event.getDeliveryId());
        System.out.println("➡ Order ID: " + event.getOrderId());
        System.out.println("➡ Status: " + event.getStatus());
        System.out.println("➡ Timestamp: " + event.getTimestamp());
    }
}
