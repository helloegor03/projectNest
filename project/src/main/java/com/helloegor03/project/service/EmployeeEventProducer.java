package com.helloegor03.project.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.helloegor03.common.dto.EmployeeCreatedEvent;

@Service
public class EmployeeEventProducer {
    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    public EmployeeEventProducer(KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmployeeAddedEvent(EmployeeCreatedEvent event) {
        kafkaTemplate.send("employee-added-topic", event);
    }
}
