package com.helloegor03.project.service;

import com.helloegor03.common.dto.ProjectCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectEventProducer {

    private final KafkaTemplate<String, ProjectCreatedEvent> kafkaTemplate;

    public ProjectEventProducer(KafkaTemplate<String, ProjectCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProjectCreatedEvent(ProjectCreatedEvent event) {
        kafkaTemplate.send("project-created-topic", event);
    }
}
