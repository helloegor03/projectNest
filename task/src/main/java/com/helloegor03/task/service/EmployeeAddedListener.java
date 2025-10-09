package com.helloegor03.task.service;

import com.helloegor03.common.dto.EmployeeCreatedEvent;
import com.helloegor03.common.dto.ProjectCreatedEvent;
import com.helloegor03.task.model.Assignee;
import com.helloegor03.task.model.Role;
import com.helloegor03.task.repository.AssigneeRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAddedListener {
    private final AssigneeRepository assigneeRepository;

    public EmployeeAddedListener(AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }

    @KafkaListener(
            topics = "employee-added-topic",
            containerFactory = "employeeAddedKafkaListenerContainerFactory"
    )
    public void consume(EmployeeCreatedEvent event) {
        Assignee assignee = new Assignee();
        assignee.setProjectId(event.getProjectId());
        assignee.setUserIdForEmployee(event.getEmployee().getUserId());
        assignee.setUsername(event.getEmployee().getUsername());
        assignee.setRole(Role.valueOf(event.getRole().name()));
        assigneeRepository.save(assignee);

    }
}
