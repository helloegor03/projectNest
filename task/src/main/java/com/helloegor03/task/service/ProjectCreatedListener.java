package com.helloegor03.task.service;

import com.helloegor03.common.dto.ProjectCreatedEvent;
import com.helloegor03.task.model.Assignee;
import com.helloegor03.task.model.Role;
import com.helloegor03.task.repository.AssigneeRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProjectCreatedListener {

    private final AssigneeRepository assigneeRepository;

    public ProjectCreatedListener(AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }

    @KafkaListener(
            topics = "project-created-topic",
            containerFactory = "projectCreatedKafkaListenerContainerFactory"
    )
    public void consume(ProjectCreatedEvent event) {
        event.getEmployees().forEach(e -> {
            Assignee assignee = new Assignee();
            assignee.setProjectId(event.getProjectId());
            assignee.setUserId(e.getUserId());
            assignee.setUsername(e.getUsername());
            assignee.setRole(Role.valueOf(event.getRole().name()));
            assigneeRepository.save(assignee);
        });
    }
}