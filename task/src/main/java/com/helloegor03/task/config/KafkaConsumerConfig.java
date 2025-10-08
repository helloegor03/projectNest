package com.helloegor03.task.config;

import com.helloegor03.common.dto.EmployeeCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.helloegor03.common.dto.ProjectCreatedEvent;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, ProjectCreatedEvent> projectCreatedConsumerFactory() {
        JsonDeserializer<ProjectCreatedEvent> deserializer = new JsonDeserializer<>(ProjectCreatedEvent.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "task-service");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProjectCreatedEvent> projectCreatedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProjectCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(projectCreatedConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EmployeeCreatedEvent> employeeAddedConsumerFactory() {
        JsonDeserializer<EmployeeCreatedEvent> deserializer = new JsonDeserializer<>(EmployeeCreatedEvent.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "task-service");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmployeeCreatedEvent> employeeAddedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmployeeCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(employeeAddedConsumerFactory());
        return factory;
    }
}
