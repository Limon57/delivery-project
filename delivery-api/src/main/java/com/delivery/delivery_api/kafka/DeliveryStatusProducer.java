package com.delivery.delivery_api.kafka;

import com.delivery.delivery_api.dto.DeliveryEventDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeliveryStatusProducer {

    private final KafkaTemplate<String, DeliveryEventDTO> kafkaTemplate;

    @Value("${delivery.kafka.topic}")
    private String topic;

    public DeliveryStatusProducer(KafkaTemplate<String, DeliveryEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDeliveryStatus(DeliveryEventDTO event) {
        kafkaTemplate.send(topic, event);
    }

}
