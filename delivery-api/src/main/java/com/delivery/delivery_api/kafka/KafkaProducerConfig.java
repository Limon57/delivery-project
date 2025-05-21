package com.delivery.delivery_api.kafka;

import com.delivery.delivery_api.dto.DeliveryEventDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration  // Marks this class as a configuration source for Spring beans
public class KafkaProducerConfig {

    // Bean that defines the Kafka producer factory
    @Bean
    public ProducerFactory<String, DeliveryEventDTO> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        // Kafka server address
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Serializer for the key (message ID or topic key)
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Serializer for the value (your DeliveryEventDTO object, sent as JSON)
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Create and return a producer factory with these configurations
        return new DefaultKafkaProducerFactory<>(configProps);
    }


    // Bean that defines the KafkaTemplate used to send messages
    @Bean
    public KafkaTemplate<String, DeliveryEventDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory()); // Uses the factory defined above
    }

}
