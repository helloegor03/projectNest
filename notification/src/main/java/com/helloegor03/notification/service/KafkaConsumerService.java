package com.helloegor03.notification.service;

import com.helloegor03.common.dto.EmployeeCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final EmailService emailService;

    public KafkaConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void consume(EmployeeCreatedEvent event) {


    }



}
